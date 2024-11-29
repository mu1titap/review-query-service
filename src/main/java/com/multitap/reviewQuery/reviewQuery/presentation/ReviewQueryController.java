package com.multitap.reviewQuery.reviewQuery.presentation;

import com.multitap.reviewQuery.common.response.BaseResponse;
import com.multitap.reviewQuery.reviewQuery.application.ReviewListService;
import com.multitap.reviewQuery.reviewQuery.dto.out.ReviewListResponseDto;
import com.multitap.reviewQuery.reviewQuery.vo.out.ReviewListResponseVo;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/reviewList")
public class ReviewQueryController {

    private final ReviewListService reviewListService;

    @Operation(summary = "멘토링별 리뷰 리스트 조회", description = "특정 멘토링에 작성된 리뷰 리스트를 무한스크롤로 조회합니다.")
    @GetMapping("/review-list/{mentoringUuid}")
    public BaseResponse<Page<ReviewListResponseVo>> getReviewListByMentoring(
            @PathVariable String mentoringUuid,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        return new BaseResponse<>(reviewListService.getReviewListByMentoringUuid(mentoringUuid, page, size)
                .map(ReviewListResponseDto::toVo));

    }

}