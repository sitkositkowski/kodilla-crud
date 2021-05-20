package com.crud.tasks.mapper;

import com.crud.tasks.domain.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class TrelloMapperTest {

    @Autowired
    private TrelloMapper trelloMapper;

    @Test
    void testMapToBoards() {
        //Given
        List<TrelloListDto> trelloListsDto = new ArrayList<>();
        trelloListsDto.add(new TrelloListDto("1","Test List", false));
        List<TrelloBoardDto> trelloBoardsDto = new ArrayList<>();
        trelloBoardsDto.add(new TrelloBoardDto("1","Test Board", trelloListsDto));
        //When
        List<TrelloBoard> trelloBoards = trelloMapper.mapToBoards(trelloBoardsDto);
        //Then
        assertEquals(1, trelloBoards.size());
        assertEquals("1", trelloBoards.get(0).getId());
        assertEquals("Test Board", trelloBoards.get(0).getName());
        assertEquals("Test List", trelloBoards.get(0).getLists().get(0).getName());
    }

    @Test
    void testMapToBoardsDto() {
        //Given
        List<TrelloList> trelloLists = new ArrayList<>();
        trelloLists.add(new TrelloList("1","Test List", false));
        List<TrelloBoard> trelloBoards= new ArrayList<>();
        trelloBoards.add(new TrelloBoard("1","Test Board", trelloLists));
        //When
        List<TrelloBoardDto> trelloBoardsDto = trelloMapper.mapToBoardsDto(trelloBoards);
        //Then
        assertEquals(1, trelloBoardsDto.size());
        assertEquals("1", trelloBoardsDto.get(0).getId());
        assertEquals("Test Board", trelloBoardsDto.get(0).getName());
        assertEquals("Test List", trelloBoardsDto.get(0).getLists().get(0).getName());
    }

    @Test
    void testMapToList() {
        //Given
        List<TrelloListDto> trelloListsDto = new ArrayList<>();
        trelloListsDto.add(new TrelloListDto("1","Test List", false));
        //When
        List<TrelloList> trelloLists = trelloMapper.mapToList(trelloListsDto);
        //Then
        assertEquals(1, trelloLists.size());
        assertEquals("1", trelloLists.get(0).getId());
        assertEquals("Test List", trelloLists.get(0).getName());
    }

    @Test
    void testMapToListDto() {
        //Given
        List<TrelloList> trelloLists = new ArrayList<>();
        trelloLists.add(new TrelloList("1","Test List", false));
        //When
        List<TrelloListDto> trelloListsDto = trelloMapper.mapToListDto(trelloLists);
        //Then
        assertEquals(1, trelloListsDto.size());
        assertEquals("1", trelloListsDto.get(0).getId());
        assertEquals("Test List", trelloListsDto.get(0).getName());
    }

    @Test
    void testMapToCardDto() {
        //Given
        TrelloCard trelloCard =  new TrelloCard("1","Test description","1","1");
        //When
        TrelloCardDto trelloCardDto = trelloMapper.mapToCardDto(trelloCard);
        //Then
        assertEquals("1", trelloCardDto.getName());
        assertEquals("Test description", trelloCardDto.getDescription());
        assertEquals("1", trelloCardDto.getPos());
        assertEquals("1", trelloCardDto.getListId());
    }

    @Test
    void testMapToCard() {
        //Given
        TrelloCardDto trelloCardDto =  new TrelloCardDto("1","Test description","1","1");
        //When
        TrelloCard trelloCard = trelloMapper.mapToCard(trelloCardDto);
        //Then
        assertEquals("1", trelloCard.getName());
        assertEquals("Test description", trelloCard.getDescription());
        assertEquals("1", trelloCard.getPos());
        assertEquals("1", trelloCard.getListId());
    }
}