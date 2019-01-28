package com.endeline.bit4.bi4;

import com.endeline.bit4.bi4.dao.three.ThreeDao;
import com.endeline.bit4.bi4.enums.Status;
import com.endeline.bit4.bi4.models.StatusEntity;
import com.endeline.bit4.bi4.models.ThreeEntity;
import com.endeline.bit4.bi4.service.three.ThreeServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;

/**
 * Class for test three service.
 * Class usr mock to perform three dao.
 */
@RunWith(MockitoJUnitRunner.class)
public class ThreeServiceTest {
    @Mock
    private ThreeDao threeDao;

    @InjectMocks
    private ThreeServiceImpl threeService;

    /**
     * Test three dao response when, service call find all entity.
     */
    @Test
    public void givenListEntity_whenFindAll_thenReturnListEntity() {
        ThreeEntity entity = new ThreeEntity(1, 1);
        ThreeEntity entity2 = new ThreeEntity(1,2, 2);

        given(threeDao.findAll()).willReturn(Arrays.asList(entity, entity2));

        List<ThreeEntity> founds = threeService.findAll();

        assertEquals(entity.getId(), founds.get(0).getId());
        assertEquals(entity.getParentId(), founds.get(0).getParentId());
        assertEquals(entity.getValue(), founds.get(0).getValue());

        assertEquals(entity2.getId(), founds.get(1).getId());
        assertEquals(entity2.getParentId(), founds.get(1).getParentId());
        assertEquals(entity2.getValue(), founds.get(1).getValue());
    }

    /**
     * Test dao when service call findById then dao will return current entity.
     */
    @Test
    public void givenEntity_whenFindById_thenReturnEntity() {
        int id = 0;
        ThreeEntity entity = new ThreeEntity(1,1);

        given(threeDao.findById(0)).willReturn(entity);

        ThreeEntity found = threeService.findById(id);

        assertEquals(id, entity.getId());
        assertEquals(found.getParentId(), entity.getParentId());
        assertEquals(found.getValue(), entity.getValue());
    }

    /**
     * test dao function findByParentId when service will call call this.
     * Dao will return list of entity where paren id equal search id.
     */
    @Test
    public void givenListEntity_whenFindByParentId_thenReturnListEntity() {
        int parentId = 1;
        ThreeEntity entity = new ThreeEntity(1,1,1);
        ThreeEntity entity2 = new ThreeEntity(2,1,1);
        List<ThreeEntity> entities = Arrays.asList(entity, entity2);

        given(threeDao.findByParentId(parentId)).willReturn(entities);

        List<ThreeEntity> found = threeService.findByParentId(parentId);

        assertEquals(2, found.size());

        assertEquals(found.get(0).getId(), entity.getId());
        assertEquals(found.get(0).getParentId(), entity.getParentId());
        assertEquals(found.get(0).getValue(), entity.getValue());

        assertEquals(found.get(1).getId(), entity2.getId());
        assertEquals(found.get(1).getParentId(), entity2.getParentId());
        assertEquals(found.get(1).getValue(), entity2.getValue());

    }

    /**
     * Test dao response when service call save(), dao will return saved entity.
     */
    @Test
    public void givenThreeEntity_whenSave_thenReturnSavedEntity() {
        ThreeEntity entity = new ThreeEntity(1,1);

        given(threeDao.save(entity)).willReturn(entity);

        ThreeEntity found = threeService.save(entity);

        assertEquals(entity.getId(), found.getId());
        assertEquals(entity.getParentId(), found.getParentId());
        assertEquals(entity.getValue(), found.getValue());
    }

    /**
     * test dao, when service call update, dao will return updadet entity.
     */
    @Test
    public void givenThreeEntity_whenUpdate_thenReturnUpdatedEntity() {
        ThreeEntity entity = new ThreeEntity(1,1);
        entity.setValue(1000);
        entity.setParentId(12);

        given(threeDao.save(entity)).willReturn(entity);

        ThreeEntity found = threeService.save(entity);

        assertEquals(entity.getId(), found.getId());
        assertEquals(entity.getParentId(), found.getParentId());
        assertEquals(entity.getValue(), found.getValue());
    }

    /**
     * Test dao when call service call delete by entity, dao will return status OK
     */
    @Test
    public void givenStatus_whenDeleteByEntity_ThenReturnStatus() {
        ThreeEntity entity = new ThreeEntity(1,1,1);

        given(threeDao.delete(entity)).willReturn(Status.OK);

        StatusEntity status = threeService.delete(entity);

        assertEquals(Status.OK.getName(), status.getStatus());
    }

    /**
     * Test dao when call service call delete by id, dao will return status OK
     */
    @Test
    public void givenStatus_whenDeleteById_ThenReturnStatus() {
        int id = 1;

        given(threeDao.deleteById(1)).willReturn(Status.OK);

        StatusEntity status = threeService.deleteById(id);

        assertEquals(Status.OK.getName(), status.getStatus());
    }
}
