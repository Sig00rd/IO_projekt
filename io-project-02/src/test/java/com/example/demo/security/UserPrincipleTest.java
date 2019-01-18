package com.example.demo.security;

import com.example.demo.entity.Role;
import com.example.demo.entity.User;
import com.example.demo.security.service.UserPrinciple;
import com.example.demo.test_utils.TestUtils;
import com.example.demo.utils.RoleName;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
public class UserPrincipleTest
{
    @Test
    public void whenBuildUserPrinciple_thenResultUserPrincipleShouldRetainUserData() {
        // given
        User seba = TestUtils.sampleValidUser();

        // when
        UserPrinciple sebaPrinciple = UserPrinciple.build(seba);

        // then
        assertThat(sebaPrinciple.getId()).isEqualTo(seba.getId());
        assertThat(sebaPrinciple.getUsername()).isEqualTo(seba.getUserName());
        assertThat(sebaPrinciple.getEmail()).isEqualTo(seba.getEmail());
        assertThat(sebaPrinciple.getPassword()).isEqualTo(seba.getPassword());
    }

    @Test
    public void whenBuildUserPrinciple_andUsersRoleIsROLE_USER_thenGrantHimCorrectAuthorities() {
        // given
        User seba = TestUtils.sampleValidUser();
        Set<Role> role_user = new HashSet<Role>();
        role_user.add(new Role(RoleName.ROLE_USER));
        seba.setRoles(role_user);

        // when
        UserPrinciple sebaPrinciple = UserPrinciple.build(seba);

        // then
        assertThat(sebaPrinciple.getAuthorities().contains(RoleName.ROLE_USER));
        assertThat(sebaPrinciple.getAuthorities().size()).isEqualTo(1);
    }

    @Test
    public void whenBuildUserPrinciple_andUsersRoleIsROLE_ADMIN_thenGrantHimCorrectAuthorities() {
        // given
        User seba = TestUtils.sampleValidUser();
        Set<Role> role_admin = new HashSet<Role>();
        role_admin.add(new Role(RoleName.ROLE_ADMIN));
        seba.setRoles(role_admin);

        // when
        UserPrinciple sebaPrinciple = UserPrinciple.build(seba);

        // then
        assertThat(sebaPrinciple.getAuthorities().contains(RoleName.ROLE_ADMIN));
        assertThat(sebaPrinciple.getAuthorities().size()).isEqualTo(1);
    }
}
