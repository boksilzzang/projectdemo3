package com.example.projectdemo.entity;

import com.example.projectdemo.dto.UserDTO;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.sql.Blob;
@Getter
@Setter
@Entity
@Table(name="userTable")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userNo;

    @Column(length = 50, nullable = false)
    private String userEmail;

    @Column(length = 50, nullable = false)
    private String userPassword;

    @Column(length = 50, nullable = false)
    private String userNickname;

    @ColumnDefault("50")
    @Column(nullable = false)
    private Long point;

    @Column
    @Lob
    private Blob userPic;

    @Column
    private String userInfo;

    public static UserEntity toSaveEntity(UserDTO userDTO) {
        UserEntity userEntity = new UserEntity();

        userEntity.setUserEmail(userDTO.getUserEmail());
        userEntity.setUserPassword(userDTO.getUserPassword());
        userEntity.setUserNickname(userDTO.getUserNickname());
        userEntity.setUserNickname(userDTO.getUserNickname());

        return userEntity;
    }
}
