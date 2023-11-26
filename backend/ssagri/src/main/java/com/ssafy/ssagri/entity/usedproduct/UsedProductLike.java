package com.ssafy.ssagri.entity.usedproduct;

import com.ssafy.ssagri.entity.user.User;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@Table(name = "used_product_like")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UsedProductLike {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "used_product_like_no")
    private Long no;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_no",nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "used_product_no",nullable = false)
    private UsedProduct usedProduct;

    @Builder
    public UsedProductLike(Long no, User user, UsedProduct usedProduct) {
        this.no = no;
        this.user = user;
        this.usedProduct = usedProduct;
    }
}
