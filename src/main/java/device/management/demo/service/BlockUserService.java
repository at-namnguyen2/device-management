package device.management.demo.service;

import device.management.demo.entity.BlockUser;

public interface BlockUserService {
	BlockUser addBlockUser(BlockUser blockUser);
	BlockUser editBlockUser(BlockUser blockUser);
	boolean deleteBlockUser(Long id);
}
