package com.enigma.swipeschat.repository;
import com.enigma.swipeschat.entity.ChatChannel;
import com.enigma.swipeschat.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ChatChannelRepository extends JpaRepository<ChatChannel,Long> {
    List<ChatChannel> findAllByUserOne(User user);
    ChatChannel findByUserOneAndUserTwo(User usr1, User usr2);
}
