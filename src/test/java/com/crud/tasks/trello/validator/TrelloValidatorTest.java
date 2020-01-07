package com.crud.tasks.trello.validator;

import com.crud.tasks.domain.TrelloBoard;
import com.crud.tasks.domain.TrelloList;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TrelloValidatorTest {

    @Autowired
    private TrelloValidator trelloValidator;

    @Test
    public void validateTrelloBoards() {
        //Given
        List<TrelloBoard> trelloBoards = new ArrayList<>();
        List<TrelloList> trelloLists = new ArrayList<>();
        TrelloBoard trelloBoard = new TrelloBoard("1","test", trelloLists);
        trelloBoards.add(trelloBoard);
        //When
        List<TrelloBoard> trelloBoards1 = trelloValidator.validateTrelloBoards(trelloBoards);
        //Then
        Assert.assertEquals(0, trelloBoards1.size());
    }
}