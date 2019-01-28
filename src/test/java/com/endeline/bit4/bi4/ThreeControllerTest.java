package com.endeline.bit4.bi4;

import com.endeline.bit4.bi4.controllers.api.ThreeController;
import com.endeline.bit4.bi4.enums.Status;
import com.endeline.bit4.bi4.models.ManyThreeEntity;
import com.endeline.bit4.bi4.models.StatusEntity;
import com.endeline.bit4.bi4.models.ThreeEntity;
import com.endeline.bit4.bi4.service.three.ThreeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Class for test controller layer.
 * Class use mock mvc to perform get/post/etc request.
 */
@RunWith(SpringRunner.class)
@WebMvcTest(ThreeController.class)
public class ThreeControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private ThreeService threeService;

    /**
     * Test a find all entities from /api/all. When user call /api/all we must return a json array with entities.
     * @throws Exception error
     */
    @Test
    public void givenListThreeEntity_whenFindAll_thenReturnJsonArray() throws Exception {
        ThreeEntity entity = new ThreeEntity(1,1);

        given(threeService.findAll())
                .willReturn(Arrays.asList(entity));

        mvc.perform(get("/api/all")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is((int)entity.getId())))
                .andExpect(jsonPath("$[0].parentId", is((int)entity.getParentId())))
                .andExpect(jsonPath("$[0].value", is((int)entity.getValue())));
    }

    /**
     * test response when user call find all and service return empty list.
     * @throws Exception error
     */
    @Test
    public void givenListThreeEntity_whenFindAll_ThenReturnNullJsonArray() throws Exception {
        given(threeService.findAll())
                .willReturn(Collections.emptyList());

        mvc.perform(get("/api/all")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));

    }

    /**
     * Test find entity by id.
     * When user call /api/1 we return existing entities.
     * @throws Exception error
     */
    @Test
    public void givenThreeEntity_whenFindById_thenReturnJsonObject() throws Exception {
        ThreeEntity entity = new ThreeEntity(1, 1);

        given(threeService.findById(1))
                .willReturn(entity);

        mvc.perform(get("/api/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is((int)entity.getId())))
                .andExpect(jsonPath("$.parentId", is((int)entity.getParentId())))
                .andExpect(jsonPath("$.value", is((int)entity.getValue())));
    }

    /**
     * When user call /api/parent/1 service must return a list of entities where parentId equals finding id.
     * @throws Exception error
     */
    @Test
    public void givenThreeEntity_whenFindByParentId_thenReturnJsonArray() throws Exception {
        ThreeEntity entity = new ThreeEntity(0,1, 1);
        ThreeEntity entity2 = new ThreeEntity(1,1, 100);
        List<ThreeEntity> entities = Arrays.asList(entity, entity2);

        given(threeService.findByParentId(1))
                .willReturn(entities);

        mvc.perform(get("/api/parent/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(0)))
                .andExpect(jsonPath("$[0].parentId", is(1)))
                .andExpect(jsonPath("$[0].value", is(1)))
                .andExpect(jsonPath("$[1].id", is(1)))
                .andExpect(jsonPath("$[1].parentId", is(1)))
                .andExpect(jsonPath("$[1].value", is(100)));
    }

    /**
     * test response when user call findByParentId and service return empty list.
     * @throws Exception error
     */
    @Test
    public void givenListThreeEntity_whenFindByParentId_ThenReturnNullJsonArray() throws Exception {
        int id = 1;

        given(threeService.findByParentId(id))
                .willReturn(Collections.emptyList());

        mvc.perform(get("/api/parent/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));

    }

    /**
     * test response when user call /api/add to save new entity.
     * @throws Exception error
     */
    @Test
    public void givenThreeEntity_whenSaveEntity_thenReturnSavedEntity() throws Exception{
        ThreeEntity entity = new ThreeEntity(5, 1000);

        given(threeService.save(entity))
                .willReturn(entity);

        mvc.perform(post("/api/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(entity)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(0)))
                .andExpect(jsonPath("$.parentId", is(5)))
                .andExpect(jsonPath("$.value", is(1000)));
    }

    /**
     * check response when user call /api/update to update entity.
     * @throws Exception error
     */
    @Test
    public void givenThreeEntity_whenUpdateEntity_thenReturnUpdatedEntity() throws Exception {
        ThreeEntity entity = new ThreeEntity(6, 4, 20);

        entity.setParentId(3);
        entity.setValue(100);

        given(threeService.update(entity))
                .willReturn(entity);

        mvc.perform(put("/api/update")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(entity)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(6)))
                .andExpect(jsonPath("$.parentId", is(3)))
                .andExpect(jsonPath("$.value", is(100)));
    }


    @Test
    public void givenManyThreeEntity_whenUpdateManyEntity_thenReturnListUpdatedEntities() throws Exception{
        ThreeEntity entity = new ThreeEntity(1,1,1);
        ThreeEntity entity2 = new ThreeEntity(2,2,2);

        entity.setParentId(10);
        entity.setValue(10);

        entity2.setParentId(20);
        entity2.setValue(20);

        List<ThreeEntity> entities = Arrays.asList(entity, entity2);

        ManyThreeEntity manyThreeEntity = new ManyThreeEntity(entities);

        given(threeService.updateMany(manyThreeEntity))
                .willReturn(entities);

        mvc.perform(put("/api/manyupdate")
            .contentType(MediaType.APPLICATION_JSON)
            .content(new ObjectMapper().writeValueAsString(manyThreeEntity)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[0].id", is((int)entity.getId())))
            .andExpect(jsonPath("$[0].parentId", is((int)entity.getParentId())))
            .andExpect(jsonPath("$[0].value", is((int)entity.getValue())))
            .andExpect(jsonPath("$[1].id", is((int)entity2.getId())))
            .andExpect(jsonPath("$[1].parentId", is((int)entity2.getParentId())))
            .andExpect(jsonPath("$[1].value", is((int)entity2.getValue())));
    }


    /**
     * Check response when user call /api/detete to delete entity by entity.
     * @throws Exception error.
     */
    @Test
    public void givenStatus_whenDeleteByEntity_thenReturnStatus() throws Exception {
        ThreeEntity entity = new ThreeEntity(0, 1, 0);

        given(threeService.delete(entity)).willReturn(new StatusEntity(Status.OK.getName()));

        mvc.perform(delete("/api/delete")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(entity)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status", is(Status.OK.getName())));
    }

    /**
     * Check response when user call /api/detete/id to delete entity by enter id.
     * @throws Exception error.
     */
    @Test
    public void givenStatus_whenDeleteById_thenReturnStatus() throws Exception {
        int id = 1;

        given(threeService.deleteById(id))
                .willReturn(new StatusEntity(Status.OK.getName()));

        mvc.perform(delete("/api/delete/{id}", id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status", is(Status.OK.getName())));
    }
}
