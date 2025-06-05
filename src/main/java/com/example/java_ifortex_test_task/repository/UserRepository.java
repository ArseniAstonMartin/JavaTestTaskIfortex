package com.example.java_ifortex_test_task.repository;

import com.example.java_ifortex_test_task.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    @Query(value = """
                SELECT DISTINCT u.*, s.started_at_utc
                FROM users u
                JOIN sessions s
                ON u.id = s.user_id
                WHERE s.device_type = 2
                ORDER BY s.started_at_utc DESC
            """, nativeQuery = true)
    List<User> getUsersWithAtLeastOneMobileSession();

    @Query(value = """
                WITH user_sessions AS (
                    SELECT user_id, COUNT(*) AS session_count
                    FROM sessions
                    GROUP BY user_id
                    ORDER BY session_count DESC
                    LIMIT 1
                )
                SELECT u.*
                FROM users u
                JOIN user_sessions us ON u.id = us.user_id
            """, nativeQuery = true)
    User getUserWithMostSessions();
}
