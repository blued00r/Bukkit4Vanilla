package keepcalm.mods.bukkit.bukkitAPI.entity;

import java.util.Set;

import keepcalm.mods.bukkit.bukkitAPI.BukkitServer;
import net.minecraft.src.AxisAlignedBB;
import net.minecraft.src.EntityDragon;
import net.minecraft.src.EntityDragonPart;
import net.minecraft.src.IEntityMultiPart;

import org.bukkit.entity.ComplexEntityPart;
import org.bukkit.entity.ComplexLivingEntity;
import org.bukkit.entity.EntityType;
import org.bukkit.event.entity.EntityDamageEvent;
//import org.bukkit.craftbukkit.CraftServer;

public class BukkitComplexPart extends BukkitEntity implements ComplexEntityPart {
	public BukkitComplexPart(BukkitServer server, EntityDragonPart entity) {
		super(server, entity);
	}

	public ComplexLivingEntity getParent() {
		return (ComplexLivingEntity) this.getEntity(this.server, getHandle().entityDragonObj);
	}

	private ComplexLivingEntity getEntity(BukkitServer server,
			IEntityMultiPart entityDragonObj) {
		return new BukkitEnderDragon(this.server, (EntityDragon) this.entity.worldObj.getEntitiesWithinAABB(EntityDragon.class, AxisAlignedBB.getAABBPool().addOrModifyAABBInPool(entity.posX + 15, entity.posY + 15, entity.posZ + 15, 0, 0, 0)).get(0));
	}

	@Override
	public void setLastDamageCause(EntityDamageEvent cause) {
		getParent().setLastDamageCause(cause);
	}

	@Override
	public EntityDamageEvent getLastDamageCause() {
		return getParent().getLastDamageCause();
	}

	@Override
	public EntityDragonPart getHandle() {
		return (EntityDragonPart) entity;
	}

	@Override
	public String toString() {
		return "CraftComplexPart";
	}

	public EntityType getType() {
		return EntityType.COMPLEX_PART;
	}
}
