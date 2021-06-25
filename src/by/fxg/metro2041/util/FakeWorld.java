package by.fxg.metro2041.util;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.logging.ILogAgent;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.profiler.Profiler;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.IProgressUpdate;
import net.minecraft.village.VillageCollection;
import net.minecraft.world.ChunkPosition;
import net.minecraft.world.EnumGameType;
import net.minecraft.world.MinecraftException;
import net.minecraft.world.World;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.WorldSettings;
import net.minecraft.world.WorldType;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.chunk.storage.IChunkLoader;
import net.minecraft.world.storage.IPlayerFileData;
import net.minecraft.world.storage.ISaveHandler;
import net.minecraft.world.storage.WorldInfo;

public class FakeWorld extends World {
   private int[][][] idArray;
   private int[][][] metaArray;
   private int factor = 3;
   private boolean replace = false;
   private final List spawnOrder = new ArrayList(5);
   private static final Profiler dummyProfiler = new Profiler();
   private static final ILogAgent dummyLogger = new FakeWorld.dudLog();

   public FakeWorld(EnumGameType type) {
      super(new FakeWorld.dudSave(), "pseudo", new FakeWorld.dudProvider(), new WorldSettings(0L, type, false, false, WorldType.DEFAULT), dummyProfiler, dummyLogger);
      this.setBlockArray(this.getDefaultIdArray());
      super.chunkProvider = new FakeWorld.dudCP(this);
      super.villageCollectionObj = new VillageCollection(this);
      super.provider.registerWorld(this);
      super.chunkProvider = this.createChunkProvider();
      this.calculateInitialSkylight();
      super.provider.calculateInitialWeather();
   }

   public int[] getDefaultIdArray() {
      int[] r = new int[27];

      for(int i = 0; i < r.length; ++i) {
         if (i < 18) {
            r[i] = 0;
         } else if (i < 9) {
            r[i] = Block.grass.blockID;
         } else {
            r[i] = Block.dirt.blockID;
         }
      }

      return r;
   }

   public int[] buildHorizontalLayers(int... i) {
      int[] builder = new int[(int)Math.pow((double)i.length, 3.0D)];
      int a = -1;
      int[] var4 = i;
      int var5 = i.length;

      for(int var6 = 0; var6 < var5; ++var6) {
         int y = var4[var6];

         for(int z = 0; z < i.length; ++z) {
            for(int x = 0; x < i.length; ++x) {
               builder[a++] = i[y];
            }
         }
      }

      return builder;
   }

   public void setCanBlockValuesBeReplaced(boolean b) {
      this.replace = b;
   }

   public void setBlockArrays(int[] ids, int[] metas) {
      try {
         if (ids.length != metas.length) {
            throw new Throwable("Id and meta arrays must be equivalent in length");
         }

         int i = 0;

         while(true) {
            if (i >= ids.length) {
               double fact = Math.cbrt((double)ids.length);
               int a = this.factor;
               this.factor = (int)fact;
               if (fact == (double)this.factor || fact == (double)this.factor && this.factor != 0) {
                  i = 0;
                  this.idArray = new int[this.factor][this.factor][this.factor];
                  this.metaArray = new int[this.factor][this.factor][this.factor];

                  for(int x = 0; x < this.factor; ++x) {
                     for(int z = 0; z < this.factor; ++z) {
                        for(int y = 0; y < this.factor; ++y) {
                           this.idArray[x][y][z] = ids[i];
                           this.metaArray[x][y][z] = metas[i];
                           ++i;
                        }
                     }
                  }
                  break;
               }

               this.factor = a;
               throw new Throwable("Block Return arrays must be a round, cubic this.factor");
            }

            if (ids[i] >= 0 && i < Block.blocksList.length) {
               if (metas[i] <= 16 && i >= 0) {
                  ++i;
                  continue;
               }

               throw new Throwable("All meta values must be within the range of 0 to 15");
            }

            throw new Throwable("All Id values must be within range of 0 to " + Block.blocksList.length);
         }
      } catch (Throwable var10) {
         var10.printStackTrace();
      }

   }

   public void setBlockArray(int[] ids) {
      int[] metas = new int[ids.length];

      for(int i = 0; i < ids.length; ++i) {
         metas[0] = 0;
      }

      this.setBlockArrays(ids, metas);
   }

   public void flushSpawnRetention() {
      this.spawnOrder.clear();
   }

   public List getSpawnOrder() {
      List l = this.spawnOrder;
      this.flushSpawnRetention();
      return l;
   }

   public Entity getLastSpawned() {
      Entity e = null;
      if (!this.spawnOrder.isEmpty()) {
         e = (Entity)this.spawnOrder.get(0);
         this.spawnOrder.remove(0);
      }

      return e;
   }

   public void tickBlocksAndAmbiance() {
      for(int x = 0; x < this.factor; ++x) {
         for(int z = 0; z < this.factor; ++z) {
            for(int y = 0; y < this.factor; ++y) {
               Block.blocksList[this.idArray[y][x][z]].updateTick(this, x, y, z, super.rand);
            }
         }
      }

   }

   public boolean setBlock(int x, int y, int z, int id) {
      if (this.replace) {
         this.idArray[y >= this.factor ? this.factor : (y < 0 ? 0 : y)][x % this.factor][z % this.factor] = id;
      }

      return this.replace;
   }

   public boolean setBlock(int x, int y, int z, int id, int meta, int flag) {
      if (this.replace) {
         this.idArray[y > this.factor ? this.factor : (y < 0 ? 0 : y)][x % this.factor][z % this.factor] = id;
         this.metaArray[y > this.factor ? this.factor : (y < 0 ? 0 : y)][x % this.factor][z % this.factor] = meta;
      }

      return this.replace;
   }

   public boolean setBlockMetadataWithNotify(int x, int y, int z, int meta, int flag) {
      if (this.replace) {
         this.metaArray[y > this.factor ? this.factor : (y < 0 ? 0 : y)][x % this.factor][z % this.factor] = meta;
      }

      return this.replace;
   }

   public void tick() {
      this.tickBlocksAndAmbiance();
      this.tickUpdates(true);
      this.updateWeather();
      this.updateEntities();
   }

   public boolean setBlockToAir(int x, int y, int z) {
      if (this.replace) {
         this.idArray[y > this.factor ? this.factor : (y < 0 ? 0 : y)][x % this.factor][z % this.factor] = 0;
      }

      return this.replace;
   }

   public boolean blockExists(int x, int y, int z) {
      return this.getBlockId(x, y, z) != 0;
   }

   public int getBlockId(int x, int y, int z) {
      return this.idArray[y > this.factor ? this.factor : (y < 0 ? 0 : y)][x % this.factor][z % this.factor];
   }

   public int getBlockMetadata(int x, int y, int z) {
      return this.idArray[y > this.factor ? this.factor : (y < 0 ? 0 : y)][x % this.factor][z % this.factor];
   }

   protected IChunkProvider createChunkProvider() {
      return new FakeWorld.dudCP(this);
   }

   public Entity getEntityByID(int i) {
      Iterator var2 = super.loadedEntityList.iterator();

      Entity e;
      do {
         if (!var2.hasNext()) {
            return null;
         }

         e = (Entity)var2.next();
      } while(e.entityId != i);

      return e;
   }

   public boolean spawnEntityInWorld(Entity e) {
      this.spawnOrder.add(e);
      return super.spawnEntityInWorld(e);
   }

   public ChunkCoordinates getSpawnPoint() {
      return new ChunkCoordinates(0, 0, 0);
   }

   private static final class dudCP implements IChunkProvider {
      private final FakeWorld theWorld;

      public dudCP(FakeWorld w) {
         this.theWorld = w;
      }

      public boolean chunkExists(int i, int j) {
         return true;
      }

      public Chunk provideChunk(int i, int j) {
         return new Chunk(this.theWorld, 0, 0);
      }

      public Chunk loadChunk(int i, int j) {
         return new Chunk(this.theWorld, 0, 0);
      }

      public void populate(IChunkProvider ichunkprovider, int i, int j) {
      }

      public boolean saveChunks(boolean flag, IProgressUpdate iprogressupdate) {
         return false;
      }

      public boolean unloadQueuedChunks() {
         return true;
      }

      public boolean canSave() {
         return false;
      }

      public String makeString() {
         return "fake world";
      }

      public List getPossibleCreatures(EnumCreatureType enumcreaturetype, int i, int j, int k) {
         return null;
      }

      public ChunkPosition findClosestStructure(World world, String s, int i, int j, int k) {
         return new ChunkPosition(0, 0, 0);
      }

      public int getLoadedChunkCount() {
         return 1;
      }

      public void recreateStructures(int i, int j) {
      }

      public void saveExtraData() {
      }
   }

   private static final class dudLog implements ILogAgent {
      private dudLog() {
      }

      public void logInfo(String s) {
      }

      @SideOnly(Side.SERVER)
      public Logger func_120013_a() {
         return FMLCommonHandler.instance().getFMLLogger();
      }

      public void logWarning(String s) {
      }

      public void logWarningFormatted(String s, Object... var2) {
      }

      public void logWarningException(String s, Throwable throwable) {
      }

      public void logSevere(String s) {
      }

      public void logSevereException(String s, Throwable throwable) {
      }

      @SideOnly(Side.CLIENT)
      public void logFine(String s) {
      }
   }

   private static final class dudSave implements ISaveHandler {
      private dudSave() {
      }

      public WorldInfo loadWorldInfo() {
         return null;
      }

      public void checkSessionLock() throws MinecraftException {
      }

      public IChunkLoader getChunkLoader(WorldProvider worldprovider) {
         return null;
      }

      public void saveWorldInfoWithPlayer(WorldInfo worldinfo, NBTTagCompound nbttagcompound) {
      }

      public void saveWorldInfo(WorldInfo worldinfo) {
      }

      public IPlayerFileData getSaveHandler() {
         return null;
      }

      public void flush() {
      }

      public File getMapFileFromName(String s) {
         return null;
      }

      public String getWorldDirectoryName() {
         return "";
      }
   }

   private static final class dudProvider extends WorldProvider {
      private dudProvider() {
      }

      public String getDimensionName() {
         return "pseudo";
      }
   }
}
