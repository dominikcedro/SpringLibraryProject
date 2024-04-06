package com.example.SpringLibrary.dto.review;


import com.example.SpringLibrary.dto.BookDTO;
import com.example.SpringLibrary.dto.ReviewDTO;

public class ReviewBookDTO {
    private ReviewDTO reviewDTO;
    private BookDTO bookDTO;

    public ReviewDTO getReviewDTO() {
        return reviewDTO;
    }

    public void setReviewDTO(ReviewDTO reviewDTO) {
        this.reviewDTO = reviewDTO;
    }

    public BookDTO getBookDTO() {
        return bookDTO;
    }

    public void setBookDTO(BookDTO bookDTO) {
        this.bookDTO = bookDTO;
    }
}
