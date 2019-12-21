package com.crud.tasks.mapper;

import com.crud.tasks.domain.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TrelloMapperTestSuit {

    @Autowired
    TrelloMapper trelloMapper;

    @Test
    public void mapToBoards() {
        //Given
        TrelloListDto trelloListDto = new TrelloListDto("ListOne", "ABC", true);
        List<TrelloListDto> trelloListsDto = new ArrayList<>();
        trelloListsDto.add(trelloListDto);

        TrelloBoardDto trelloBoardDto = new TrelloBoardDto("BoardOne", "ABC",  trelloListsDto);
        List<TrelloBoardDto> trelloBoardsDto = new ArrayList<>();
        trelloBoardsDto.add(trelloBoardDto);

        //When
        List<TrelloBoard> trelloBoards = trelloMapper.mapToBoards(trelloBoardsDto);

        //Then
        assertEquals(1, trelloBoards.size());
        assertEquals("BoardOne", trelloBoards.get(0).getName());
        assertEquals("ABC", trelloBoards.get(0).getId());
    }

    @Test
    public void mapToBoardsDto() {
        //Given
        TrelloList trelloList = new TrelloList("ListOne", "ABC", true);
        List<TrelloList> trelloLists = new ArrayList<>();
        trelloLists.add(trelloList);

        TrelloBoard trelloBoard = new TrelloBoard("BoardOne", "ABC",  trelloLists);
        List<TrelloBoard> trelloBoards = new ArrayList<>();
        trelloBoards.add(trelloBoard);

        //When
        List<TrelloBoardDto> trelloBoardsDto = trelloMapper.mapToBoardsDto(trelloBoards);

        //Then
        assertEquals(1, trelloBoardsDto.size());
        assertEquals("BoardOne", trelloBoardsDto.get(0).getId());
        assertEquals("ABC", trelloBoardsDto.get(0).getName());
    }

    @Test
    public void mapToLists() {
        //Given
        List<TrelloListDto> trelloListsDto = new ArrayList<>();
        trelloListsDto.add(new TrelloListDto("ListOne", "ABC",  true));

        //When
        List<TrelloList> trelloLists = trelloMapper.mapToList(trelloListsDto);
        List<TrelloList> trelloListsEmpty = trelloMapper.mapToList(Collections.emptyList());

        //Then
        assertEquals(1, trelloLists.size());
        assertTrue(trelloLists.contains(new TrelloList("ListOne", "ABC",  true)));
        assertEquals(0, trelloListsEmpty.size());
    }

    @Test
    public void mapToListsDto() {
        //Given
        List<TrelloList> trelloLists = new ArrayList<>();
        trelloLists.add(new TrelloList("ListOne", "ABC",  true));

        //When
        List<TrelloListDto> trelloListsDto = trelloMapper.mapToListDto(trelloLists);
        List<TrelloListDto> trelloListsDtoEmpty = trelloMapper.mapToListDto(Collections.emptyList());

        //Then
        assertEquals(1, trelloListsDto.size());
        assertEquals("ListOne", trelloListsDto.get(0).getId());
        assertEquals("ABC", trelloListsDto.get(0).getName());
        assertEquals(0, trelloListsDtoEmpty.size());
    }

    @Test
    public void mapToCard() {
        //Given
        TrelloCardDto trelloCardDto = new TrelloCardDto("CardOne", "Description", "POS", "ID1");

        //When
        TrelloCard trelloCard = trelloMapper.mapToTrelloCard(trelloCardDto);

        //Then
        assertEquals(new TrelloCard("CardOne", "Description", "POS", "ID1"), trelloCard);
    }

    @Test
    public void mapToCardDto() {
        //Given
        TrelloCard trelloCard = new TrelloCard( "CardOne", "Description", "POS", "ID1");

        //When
        TrelloCardDto trelloCardDto = trelloMapper.mapToCardDto(trelloCard);

        //Then
        assertEquals(new TrelloCardDto("CardOne", "Description", "POS", "ID1"), trelloCardDto);
    }
}