package com.secret.model.users;

import com.secret.model.portfolios.Portfolio;
import com.secret.model.ranking.Rank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class User {
    private String userId;
    private Rank rank;
    private Set<User> friends;
    private List<Portfolio> portfolios;
}
