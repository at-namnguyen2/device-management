package device.management.demo.service.impl;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import device.management.demo.repository.BlockUserRepository;
import device.management.demo.service.BlockUserService;
import device.management.demo.entity.BlockUser;

@Service
public class BlockUserServiceImpl implements BlockUserService {
    @Autowired
    private BlockUserRepository blockUserRepository;
	@Override
	public boolean deleteBlockUser(Long id) {
		Optional<BlockUser> optionalBlockUser = blockUserRepository.findById(id);
		if(!optionalBlockUser.isPresent()) {
			return false;
		}
		blockUserRepository.deleteBlockUserById(id);
		return true;	
	}
	
	@Override
	public BlockUser addBlockUser(BlockUser blockUser) {
		return blockUserRepository.save(blockUser);
	}

	@Override
	public BlockUser editBlockUser(BlockUser blockUser) {
		return blockUserRepository.save(blockUser);
	}

}
