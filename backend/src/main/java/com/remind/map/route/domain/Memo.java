package com.remind.map.route.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Getter
@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Memo {

    private static final int MAX_MEMO_LENGTH = 255;

    @Column(name = "memo", nullable = false)
    private String value;

    public Memo(String value) {
        this.value = value;
    }
}