package dmu.noonsub_backend.domain.openbanking.dto;

import dmu.noonsub_backend.domain.member.entity.Member;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class SyncCompletedEvent {
    private final Member member;
}
