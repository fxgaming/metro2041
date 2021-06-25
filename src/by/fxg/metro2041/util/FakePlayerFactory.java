package by.fxg.metro2041.util;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class FakePlayerFactory {
   private String username = Minecraft.getMinecraft().getSession().getUsername();
   private World world = FakeWorldFactory.getDefault();
   private FakePlayer fakePlayer;

   public FakePlayerFactory() {
      this.fakePlayer = new FakePlayer(this.world, this.username);
   }

   public EntityPlayer getFakePlayer() {
      return this.fakePlayer;
   }

   public void setFakePlayer(EntityPlayer player) {
      this.fakePlayer = (FakePlayer)player;
   }
}
