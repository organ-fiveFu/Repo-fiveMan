package com.nurse.healthy.service.Impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.lang.tree.Tree;
import cn.hutool.core.lang.tree.TreeNodeConfig;
import cn.hutool.core.lang.tree.TreeUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.nurse.healthy.component.SnowflakeComponent;
import com.nurse.healthy.exception.ResponseEnum;
import com.nurse.healthy.mapper.SysBedInfoMapper;
import com.nurse.healthy.mapper.SysBuildingInfoMapper;
import com.nurse.healthy.mapper.SysFloorInfoMapper;
import com.nurse.healthy.mapper.SysRoomInfoMapper;
import com.nurse.healthy.model.entity.bed.SysBedInfo;
import com.nurse.healthy.model.entity.bed.SysBuildingInfo;
import com.nurse.healthy.model.entity.bed.SysFloorInfo;
import com.nurse.healthy.model.entity.bed.SysRoomInfo;
import com.nurse.healthy.model.po.bed.*;
import com.nurse.healthy.model.vo.PageVO;
import com.nurse.healthy.model.vo.bed.*;
import com.nurse.healthy.result.UserInfoToken;
import com.nurse.healthy.service.BedService;
import com.nurse.healthy.util.BeanHelper;
import com.nurse.healthy.util.OperateUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
@Transactional(rollbackFor = Exception.class)
public class BedServiceImpl implements BedService {

    @Resource
    private SysBuildingInfoMapper sysBuildingInfoMapper;

    @Resource
    private SysFloorInfoMapper sysFloorInfoMapper;

    @Resource
    private SysBedInfoMapper sysBedInfoMapper;

    @Resource
    private SnowflakeComponent snowflakeComponent;

    @Resource
    private SysRoomInfoMapper sysRoomInfoMapper;

    /**
     * 新增楼宇信息
     * @author linxiazhu
     * @date 17:17 2021/6/24
     * @param sysBuildingInfoInsertPO  入参
     * @param userInfoToken   token
     * @return  boolean
     */
    @Override
    public boolean insertSysBuildingInfo(SysBuildingInfoInsertPO sysBuildingInfoInsertPO, UserInfoToken userInfoToken) {
        //判断楼宇是否重复
        Example example = new Example(SysBuildingInfo.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("buildingCode", sysBuildingInfoInsertPO.getBuildingCode());
        criteria.andEqualTo("isDel", 0);
        int count = sysBuildingInfoMapper.selectCountByExample(example);
        if(count > 0){
            throw ResponseEnum.CODE_ALREADY_EXISTS.newException("该楼宇编码已存在,无法添加");
        }
        //新增
        SysBuildingInfo sysBuildingInfo = new SysBuildingInfo();
        BeanUtils.copyProperties(sysBuildingInfoInsertPO, sysBuildingInfo);
        Long id = snowflakeComponent.getInstance().nextId();
        OperateUtil.onSaveNew(sysBuildingInfo, userInfoToken, id);
        log.info("新增楼宇信息,入参sysBuildingInfo:" + sysBuildingInfo);
        try {
            sysBuildingInfoMapper.insertSelective(sysBuildingInfo);
        }catch (Exception e){
            log.error("新增楼宇信息失败");
            throw ResponseEnum.FILE_INSERT_FAIL.newException("新增楼宇信息失败");
        }
        return true;
    }

    /**
     * 更新楼宇信息
     * @author linxiazhu
     * @date 16:39 2021/6/25
     * @param sysBuildingInfoUpdatePO  入参
     * @param userInfoToken   token
     * @return  boolean
     */
    @Override
    public boolean updateSysBuildingInfo(SysBuildingInfoUpdatePO sysBuildingInfoUpdatePO, UserInfoToken userInfoToken) {
        //判断楼宇是否重复
        Example example = new Example(SysBuildingInfo.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("buildingCode", sysBuildingInfoUpdatePO.getBuildingCode());
        criteria.andEqualTo("isDel", 0);
        criteria.andNotEqualTo("id", sysBuildingInfoUpdatePO.getId());
        int count = sysBuildingInfoMapper.selectCountByExample(example);
        if(count > 0){
            throw ResponseEnum.CODE_ALREADY_EXISTS.newException("该楼宇编码已存在,无法更新");
        }
        //更新
        SysBuildingInfo sysBuildingInfo = new SysBuildingInfo();
        BeanUtils.copyProperties(sysBuildingInfoUpdatePO, sysBuildingInfo);
        sysBuildingInfo.setUpdaterId(userInfoToken.getUserId());
        sysBuildingInfo.setUpdateTime(new Date());
        log.info("更新楼宇信息,入参sysBuildingInfo:" + sysBuildingInfo);
        try {
            sysBuildingInfoMapper.updateByPrimaryKeySelective(sysBuildingInfo);
        }catch (Exception e){
            log.error("更新楼宇信息失败");
            throw ResponseEnum.FILE_INSERT_FAIL.newException("更新楼宇信息失败");
        }
        return false;
    }

    /**
     * 查询楼宇信息
     * @author linxiazhu
     * @date 17:28 2021/6/25
     * @param sysBuildingInfoQueryPO  入参
     * @param userInfoToken   token
     * @return  com.nurse.healthy.model.vo.PageVO<com.nurse.healthy.model.vo.bed.SysBuildingInfoQueryVO>
     */
    @Override
    public PageVO<SysBuildingInfoQueryVO> querySysBuildingInfoList(SysBuildingInfoQueryPO sysBuildingInfoQueryPO, UserInfoToken userInfoToken) {
        Page<SysBuildingInfoQueryVO> result = PageHelper.startPage(sysBuildingInfoQueryPO.getPageNum(), sysBuildingInfoQueryPO.getPageSize());
        Example example = new Example(SysBuildingInfo.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("isDel", 0);
        if(Strings.isNotBlank(sysBuildingInfoQueryPO.getKeyWords())){
            Example.Criteria criteria1 = example.createCriteria();
            criteria1.orLike("buildingCode","%"+sysBuildingInfoQueryPO.getKeyWords()+"%");
            criteria1.orLike("name","%"+sysBuildingInfoQueryPO.getKeyWords()+"%");
            example.and(criteria1);
        }
        example.orderBy("buildingCode");
        List<SysBuildingInfo> sysBuildingInfoList = sysBuildingInfoMapper.selectByExample(example);
        if(CollectionUtil.isEmpty(sysBuildingInfoList)) {
            return new PageVO<>(result.getPageNum(), result.getPageSize(), result.getTotal(), result.getPages(), new ArrayList<>());
        }
        List<SysBuildingInfoQueryVO> sysBuildingInfoQueryVOS = BeanHelper.copyWithCollection(sysBuildingInfoList, SysBuildingInfoQueryVO.class);
        return new PageVO<>(result.getPageNum(), result.getPageSize(), result.getTotal(), result.getPages(), sysBuildingInfoQueryVOS);
    }

    /**
     * 删除楼宇信息
     * @author linxiazhu
     * @date 13:41 2021/6/27
     * @param id  id
     * @param userInfoToken   token
     * @return  boolean
     */
    @Override
    public boolean deleteSysBuildingInfo(Long id, UserInfoToken userInfoToken) {
        //查询楼宇信息
        SysBuildingInfo sysBuildingInfo = sysBuildingInfoMapper.selectByPrimaryKey(id);
        //判断该楼宇是否有楼层关系
        Example example = new Example(SysFloorInfo.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("isDel", 0);
        criteria.andEqualTo("buildingCode", sysBuildingInfo.getBuildingCode());
        int count = sysFloorInfoMapper.selectCountByExample(example);
        if(count > 0){
            throw ResponseEnum.CODE_ALREADY_EXISTS.newException("该楼宇存在楼层，无法删除");
        }
        //删除楼宇
        sysBuildingInfo.setUpdateTime(new Date());
        sysBuildingInfo.setUpdaterId(userInfoToken.getUserId());
        sysBuildingInfo.setIsDel(1);
        log.info("删除楼宇信息,入参sysBuildingInfo:" + sysBuildingInfo);
        try {
            sysBuildingInfoMapper.updateByPrimaryKeySelective(sysBuildingInfo);
        }catch (Exception e){
            log.error("删除楼宇信息失败");
            throw ResponseEnum.DATA_NOT_FOUND.newException("删除楼宇信息失败");
        }
        return true;
    }

    /**
     * 新增楼层信息
     * @author linxiazhu
     * @date 13:58 2021/6/27
     * @param sysFloorInfoInsertPO  入参
     * @param userInfoToken   token
     * @return  boolean
     */
    @Override
    public boolean insertSysFloorInfo(SysFloorInfoInsertPO sysFloorInfoInsertPO, UserInfoToken userInfoToken) {
        //判断该楼宇下的楼层是否重复
        Example example =  new Example(SysFloorInfo.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("isDel", 0);
        criteria.andEqualTo("floorCode", sysFloorInfoInsertPO.getFloorCode());
        criteria.andEqualTo("buildingCode", sysFloorInfoInsertPO.getBuildingCode());
        int count = sysFloorInfoMapper.selectCountByExample(example);
        if(count > 0){
            throw ResponseEnum.CODE_ALREADY_EXISTS.newException("该楼宇下的楼层编码已存在,无法添加");
        }
        SysFloorInfo sysFloorInfo = new SysFloorInfo();
        BeanUtils.copyProperties(sysFloorInfoInsertPO, sysFloorInfo);
        Long id = snowflakeComponent.getInstance().nextId();
        OperateUtil.onSaveNew(sysFloorInfo, userInfoToken, id);
        log.info("新增楼层信息,入参sysFloorInfo" + sysFloorInfo);
        try {
            sysFloorInfoMapper.insertSelective(sysFloorInfo);
        }catch (Exception e){
            log.error("新增楼层信息失败");
            throw ResponseEnum.FILE_INSERT_FAIL.newException("新增楼层信息失败");
        }
        return true;
    }

    /**
     * 更新楼层信息
     * @author linxiazhu
     * @date 14:10 2021/6/27
     * @param sysFloorInfoUpdatePO  入参
     * @param userInfoToken   token
     * @return  boolean
     */
    @Override
    public boolean updateSysFloorInfo(SysFloorInfoUpdatePO sysFloorInfoUpdatePO, UserInfoToken userInfoToken) {
        //判断该楼宇下的楼层是否重复
        Example example = new Example(SysFloorInfo.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("floorCode", sysFloorInfoUpdatePO.getFloorCode());
        criteria.andEqualTo("buildingCode", sysFloorInfoUpdatePO.getBuildingCode());
        criteria.andEqualTo("isDel", 0);
        criteria.andNotEqualTo("id", sysFloorInfoUpdatePO.getId());
        int count = sysFloorInfoMapper.selectCountByExample(example);
        if(count > 0){
            throw ResponseEnum.CODE_ALREADY_EXISTS.newException("该楼宇下的楼层编码已存在,无法更新");
        }
        SysFloorInfo sysFloorInfo = new SysFloorInfo();
        BeanUtils.copyProperties(sysFloorInfoUpdatePO, sysFloorInfo);
        sysFloorInfo.setUpdaterId(userInfoToken.getUserId());
        sysFloorInfo.setUpdateTime(new Date());
        log.info("更新楼层信息,入参sysFloorInfo:" + sysFloorInfo);
        try {
            sysFloorInfoMapper.updateByPrimaryKeySelective(sysFloorInfo);
        }catch (Exception e){
            log.error("更新楼层信息失败");
            throw ResponseEnum.FILE_INSERT_FAIL.newException("更新楼层信息失败");
        }
        return true;
    }

    /**
     * 查询楼层信息
     * @author linxiazhu
     * @date 14:25 2021/6/27
     * @param sysFloorInfoQueryPO  入参
     * @param userInfoToken   token
     * @return  com.nurse.healthy.model.vo.PageVO<com.nurse.healthy.model.vo.bed.SysFloorInfoQueryVO>
     */
    @Override
    public PageVO<SysFloorInfoQueryVO> querySysFloorInfoList(SysFloorInfoQueryPO sysFloorInfoQueryPO, UserInfoToken userInfoToken) {
        Page<SysFloorInfoQueryVO> result = PageHelper.startPage(sysFloorInfoQueryPO.getPageNum(), sysFloorInfoQueryPO.getPageSize());
        //查询楼宇信息
        SysBuildingInfo sysBuildingInfo = sysBuildingInfoMapper.selectByPrimaryKey(sysFloorInfoQueryPO.getId());
        //查询楼层信息
        Example example = new Example(SysFloorInfo.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("buildingCode", sysBuildingInfo.getBuildingCode());
        criteria.andEqualTo("isDel", 0);
        if(Strings.isNotBlank(sysFloorInfoQueryPO.getKeyWords())){
            Example.Criteria criteria1 = example.createCriteria();
            criteria1.orLike("floorCode","%"+sysFloorInfoQueryPO.getKeyWords()+"%");
            criteria1.orLike("name","%"+sysFloorInfoQueryPO.getKeyWords()+"%");
            example.and(criteria1);
        }
        example.orderBy("floorCode");
        List<SysFloorInfo> sysFloorInfoList = sysFloorInfoMapper.selectByExample(example);
        if(CollectionUtil.isEmpty(sysFloorInfoList)){
            return new PageVO<>(result.getPageNum(), result.getPageSize(), result.getTotal(), result.getPages(), new ArrayList<>());
        }
        List<SysFloorInfoQueryVO> sysFloorInfoQueryVOList = BeanHelper.copyWithCollection(sysFloorInfoList, SysFloorInfoQueryVO.class);
        return new PageVO<>(result.getPageNum(), result.getPageSize(), result.getTotal(), result.getPages(), sysFloorInfoQueryVOList);
    }

    /**
     * 删除楼层信息
     * @author linxiazhu
     * @date 14:40 2021/6/27
     * @param id  id
     * @param userInfoToken   token
     * @return  boolean
     */
    @Override
    public boolean deleteSysFloorInfo(Long id, UserInfoToken userInfoToken) {
        //查询楼层信息
        SysFloorInfo sysFloorInfo = sysFloorInfoMapper.selectByPrimaryKey(id);
        //判断该楼层是否有房间关系
        Example example = new Example(SysRoomInfo.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("isDel", 0);
        criteria.andEqualTo("floorCode", sysFloorInfo.getFloorCode());
        int count = sysRoomInfoMapper.selectCountByExample(example);
        if(count > 0){
            throw ResponseEnum.CODE_ALREADY_EXISTS.newException("该楼层存在房间，无法删除");
        }
        //删除
        sysFloorInfo.setIsDel(1);
        sysFloorInfo.setUpdateTime(new Date());
        sysFloorInfo.setUpdaterId(userInfoToken.getUserId());
        log.info("删除楼层信息,入参sysFloorInfo:" + sysFloorInfo);
        try {
            sysFloorInfoMapper.updateByPrimaryKeySelective(sysFloorInfo);
        }catch (Exception e){
            log.error("删除楼层信息失败");
            throw ResponseEnum.DATA_NOT_FOUND.newException("删除楼层信息失败");
        }
        return true;
    }

    /**
     * 新增房间信息
     * @author linxiazhu
     * @date 10:19 2021/6/28
     * @param sysRoomInfoInsertPO  入参
     * @param userInfoToken   token
     * @return  boolean
     */
    @Override
    public boolean insertSysRoomInfo(SysRoomInfoInsertPO sysRoomInfoInsertPO, UserInfoToken userInfoToken) {
        //判断同栋楼下同楼层是否有同房间编号
        Example example = new Example(SysRoomInfo.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("buildingCode", sysRoomInfoInsertPO.getBuildingCode());
        criteria.andEqualTo("floorCode", sysRoomInfoInsertPO.getFloorCode());
        criteria.andEqualTo("roomCode", sysRoomInfoInsertPO.getRoomCode());
        int count = sysRoomInfoMapper.selectCountByExample(example);
        if(count > 0){
            throw ResponseEnum.CODE_ALREADY_EXISTS.newException("该栋楼下该楼层的房间编号已存在,无法添加");
        }
        //新增
        SysRoomInfo sysRoomInfo = new SysRoomInfo();
        BeanUtils.copyProperties(sysRoomInfoInsertPO, sysRoomInfo);
        Long id = snowflakeComponent.getInstance().nextId();
        OperateUtil.onSaveNew(sysRoomInfo, userInfoToken, id);
        log.info("新增房间信息,入参sysRoomInfo" + sysRoomInfo);
        try {
            sysRoomInfoMapper.insertSelective(sysRoomInfo);
        }catch (Exception e){
            log.error("新增房间信息失败");
            throw ResponseEnum.FILE_INSERT_FAIL.newException("新增房间信息失败");
        }
        return true;
    }

    /**
     * 更新房间信息
     * @author linxiazhu
     * @date 11:24 2021/6/28
     * @param sysRoomInfoUpdatePO  入参
     * @param userInfoToken   token
     * @return  boolean
     */
    @Override
    public boolean updateSysRoomInfo(SysRoomInfoUpdatePO sysRoomInfoUpdatePO, UserInfoToken userInfoToken) {
        //判断同栋楼下同楼层是否有同房间编号
        Example example = new Example(SysRoomInfo.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("buildingCode", sysRoomInfoUpdatePO.getBuildingCode());
        criteria.andEqualTo("floorCode", sysRoomInfoUpdatePO.getFloorCode());
        criteria.andEqualTo("roomCode", sysRoomInfoUpdatePO.getRoomCode());
        criteria.andNotEqualTo("id", sysRoomInfoUpdatePO.getId());
        int count = sysRoomInfoMapper.selectCountByExample(example);
        if(count > 0){
            throw ResponseEnum.CODE_ALREADY_EXISTS.newException("该栋楼下该楼层的房间编号已存在,无法更新");
        }
        //更新
        SysRoomInfo sysRoomInfo = new SysRoomInfo();
        BeanUtils.copyProperties(sysRoomInfoUpdatePO, sysRoomInfo);
        sysRoomInfo.setUpdaterId(userInfoToken.getUserId());
        sysRoomInfo.setUpdateTime(new Date());
        log.info("更新房间信息,入参sysRoomInfo:" + sysRoomInfo);
        try {
            sysRoomInfoMapper.updateByPrimaryKeySelective(sysRoomInfo);
        }catch (Exception e){
            log.error("更新房间信息失败");
            throw ResponseEnum.FILE_INSERT_FAIL.newException("更新房间信息失败");
        }
        return true;
    }

    /**
     * 查询房间信息
     * @author linxiazhu
     * @date 13:51 2021/6/28
     * @param sysRoomInfoQueryPO  入参
     * @param userInfoToken   token
     * @return  com.nurse.healthy.model.vo.PageVO<com.nurse.healthy.model.vo.bed.SysRoomInfoQueryVO>
     */
    @Override
    public PageVO<SysRoomInfoQueryVO> querySysRoomInfoList(SysRoomInfoQueryPO sysRoomInfoQueryPO, UserInfoToken userInfoToken) {
        Page<SysRoomInfoQueryVO> result = PageHelper.startPage(sysRoomInfoQueryPO.getPageNum(), sysRoomInfoQueryPO.getPageSize());
        //查询楼层信息
        SysFloorInfo sysFloorInfo = sysFloorInfoMapper.selectByPrimaryKey(sysRoomInfoQueryPO.getId());
        //查询房间信息
        Example example = new Example(SysRoomInfo.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("buildingCode", sysFloorInfo.getBuildingCode());
        criteria.andEqualTo("floorCode", sysFloorInfo.getFloorCode());
        criteria.andEqualTo("isDel", 0);
        if(Strings.isNotBlank(sysRoomInfoQueryPO.getKeyWords())){
            Example.Criteria criteria1 = example.createCriteria();
            criteria1.orLike("roomCode","%"+sysRoomInfoQueryPO.getKeyWords()+"%");
            criteria1.orLike("name","%"+sysRoomInfoQueryPO.getKeyWords()+"%");
            example.and(criteria1);
        }
        example.orderBy("roomCode");
        List<SysRoomInfo> sysRoomInfoList = sysRoomInfoMapper.selectByExample(example);
        if(CollectionUtil.isEmpty(sysRoomInfoList)){
            return new PageVO<>(result.getPageNum(), result.getPageSize(), result.getTotal(), result.getPages(), new ArrayList<>());
        }
        List<SysRoomInfoQueryVO> sysRoomInfoQueryVOList = BeanHelper.copyWithCollection(sysRoomInfoList, SysRoomInfoQueryVO.class);
        return new PageVO<>(result.getPageNum(), result.getPageSize(), result.getTotal(), result.getPages(), sysRoomInfoQueryVOList);
    }

    /**
     * 删除房间信息
     * @author linxiazhu
     * @date 16:17 2021/6/28
     * @param id  id
     * @param userInfoToken   token
     * @return  boolean
     */
    @Override
    public boolean deleteSysRoomInfo(Long id, UserInfoToken userInfoToken) {
        //查询房间信息
        SysRoomInfo sysRoomInfo = sysRoomInfoMapper.selectByPrimaryKey(id);
        //判断该房间是否有床位关系
        Example example = new Example(SysBedInfo.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("buildingCode", sysRoomInfo.getBuildingCode());
        criteria.andEqualTo("floorCode", sysRoomInfo.getFloorCode());
        criteria.andEqualTo("roomCode", sysRoomInfo.getRoomCode());
        criteria.andEqualTo("isDel", 0);
        int count = sysBedInfoMapper.selectCountByExample(example);
        if(count > 0){
            throw ResponseEnum.CODE_ALREADY_EXISTS.newException("该房间存在床位,无法删除");
        }
        //删除
        sysRoomInfo.setUpdateTime(new Date());
        sysRoomInfo.setUpdaterId(userInfoToken.getUserId());
        sysRoomInfo.setIsDel(1);
        log.info("删除房间信息,入参sysFloorInfo:" + sysRoomInfo);
        try {
            sysRoomInfoMapper.updateByPrimaryKeySelective(sysRoomInfo);
        }catch (Exception e){
            log.error("删除房间信息失败");
            throw ResponseEnum.DATA_NOT_FOUND.newException("删除房间信息失败");
        }
        return true;
    }

    /**
     * 新增床位信息
     * @author linxiazhu
     * @date 16:50 2021/6/28
     * @param sysBedInfoInsertPO  入参
     * @param userInfoToken   token
     * @return  boolean
     */
    @Override
    public boolean insertSysBedInfo(SysBedInfoInsertPO sysBedInfoInsertPO, UserInfoToken userInfoToken) {
        //判断该栋楼该楼层该房间是否有相同床位编码
        Example example = new Example(SysBedInfo.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("BuildingCode", sysBedInfoInsertPO.getBuildingCode());
        criteria.andEqualTo("floorCode", sysBedInfoInsertPO.getFloorCode());
        criteria.andEqualTo("roomCode", sysBedInfoInsertPO.getRoomCode());
        criteria.andEqualTo("bedCode", sysBedInfoInsertPO.getBedCode());
        criteria.andEqualTo("isDel", 0);
        int count = sysBedInfoMapper.selectCountByExample(example);
        if(count > 0){
            throw ResponseEnum.CODE_ALREADY_EXISTS.newException("该栋楼该楼层该房间已存在该床位编码,无法新增");
        }
        //新增
        SysBedInfo sysBedInfo = new SysBedInfo();
        BeanUtils.copyProperties(sysBedInfoInsertPO, sysBedInfo);
        Long id = snowflakeComponent.getInstance().nextId();
        OperateUtil.onSaveNew(sysBedInfo, userInfoToken, id);
        log.info("新增床位信息,入参sysBedInfo" + sysBedInfo);
        try {
            sysBedInfoMapper.insertSelective(sysBedInfo);
        }catch (Exception e){
            log.error("新增床位信息失败");
            throw ResponseEnum.FILE_INSERT_FAIL.newException("新增床位信息失败");
        }
        return true;
    }

    /**
     * 更新床位信息
     * @author linxiazhu
     * @date 17:03 2021/6/28
     * @param sysBedInfoUpdatePO  入参
     * @param userInfoToken   token
     * @return  boolean
     */
    @Override
    public boolean updateSysBedInfo(SysBedInfoUpdatePO sysBedInfoUpdatePO, UserInfoToken userInfoToken) {
        //判断该栋楼该楼层该房间是否有相同床位编码
        Example example = new Example(SysBedInfo.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("BuildingCode", sysBedInfoUpdatePO.getBuildingCode());
        criteria.andEqualTo("floorCode", sysBedInfoUpdatePO.getFloorCode());
        criteria.andEqualTo("roomCode", sysBedInfoUpdatePO.getRoomCode());
        criteria.andEqualTo("bedCode", sysBedInfoUpdatePO.getBedCode());
        criteria.andEqualTo("isDel", 0);
        criteria.andNotEqualTo("id", sysBedInfoUpdatePO.getId());
        int count = sysBedInfoMapper.selectCountByExample(example);
        if(count > 0){
            throw ResponseEnum.CODE_ALREADY_EXISTS.newException("该栋楼该楼层该房间已存在该床位编码,无法更新");
        }
        //更新
        SysBedInfo sysBedInfo = new SysBedInfo();
        BeanUtils.copyProperties(sysBedInfoUpdatePO, sysBedInfo);
        sysBedInfo.setUpdaterId(userInfoToken.getUserId());
        sysBedInfo.setUpdateTime(new Date());
        log.info("更新床位信息,入参sysBedInfo" + sysBedInfo);
        try {
            sysBedInfoMapper.insertSelective(sysBedInfo);
        }catch (Exception e){
            log.error("更新床位信息失败");
            throw ResponseEnum.FILE_INSERT_FAIL.newException("更新床位信息失败");
        }
        return true;
    }

    /**
     * 查询床位信息
     * @author linxiazhu
     * @date 17:11 2021/6/28
     * @param sysBedInfoQueryPO  入参
     * @param userInfoToken   token
     * @return  com.nurse.healthy.model.vo.PageVO<com.nurse.healthy.model.vo.bed.SysBedInfoQueryVO>
     */
    @Override
    public PageVO<SysBedInfoQueryVO> querySysBedInfoList(SysBedInfoQueryPO sysBedInfoQueryPO, UserInfoToken userInfoToken) {
        Page<SysBedInfoQueryVO> result = PageHelper.startPage(sysBedInfoQueryPO.getPageNum(), sysBedInfoQueryPO.getPageSize());
        //查询房间信息
        SysRoomInfo sysRoomInfo = sysRoomInfoMapper.selectByPrimaryKey(sysBedInfoQueryPO.getId());
        //查询床位信息
        Example example = new Example(SysBedInfo.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("buildingCode", sysRoomInfo.getBuildingCode());
        criteria.andEqualTo("floorCode", sysRoomInfo.getFloorCode());
        criteria.andEqualTo("roomCode", sysRoomInfo.getRoomCode());
        criteria.andEqualTo("isDel", 0);
        if(Strings.isNotBlank(sysBedInfoQueryPO.getKeyWords())){
            Example.Criteria criteria1 = example.createCriteria();
            criteria1.orLike("bedCode","%"+sysBedInfoQueryPO.getKeyWords()+"%");
            criteria1.orLike("name","%"+sysBedInfoQueryPO.getKeyWords()+"%");
            example.and(criteria1);
        }
        if("1".equals(sysBedInfoQueryPO.getUsedFlag())){
            criteria.andEqualTo("useFlag", 0);
        }
        example.orderBy("bedCode");
        List<SysBedInfo> sysBedInfoList = sysBedInfoMapper.selectByExample(example);
        if(CollectionUtil.isEmpty(sysBedInfoList)){
            return new PageVO<>(result.getPageNum(), result.getPageSize(), result.getTotal(), result.getPages(), new ArrayList<>());
        }
        List<SysBedInfoQueryVO> sysBedInfoQueryVOList = BeanHelper.copyWithCollection(sysBedInfoList, SysBedInfoQueryVO.class);
        return new PageVO<>(result.getPageNum(), result.getPageSize(), result.getTotal(), result.getPages(), sysBedInfoQueryVOList);
    }

    /**
     * 删除床位信息
     * @author linxiazhu
     * @date 17:25 2021/6/28
     * @param id  id
     * @param userInfoToken   token
     * @return  boolean
     */
    @Override
    public boolean deleteSysBedInfo(Long id, UserInfoToken userInfoToken) {
        SysBedInfo sysBedInfo = sysBedInfoMapper.selectByPrimaryKey(id);
        sysBedInfo.setUpdateTime(new Date());
        sysBedInfo.setUpdaterId(userInfoToken.getUserId());
        sysBedInfo.setIsDel(1);
        log.info("删除床位信息,入参sysBedInfo:" + sysBedInfo);
        try {
            sysBedInfoMapper.updateByPrimaryKeySelective(sysBedInfo);
        }catch (Exception e){
            log.error("删除床位信息失败");
            throw ResponseEnum.DATA_NOT_FOUND.newException("删除床位信息失败");
        }
        return true;
    }

    /**
     * 获取楼宇下拉列表
     * @author linxiazhu
     * @date 9:29 2021/6/29
     * @param userInfoToken   token
     * @return  java.util.List<com.nurse.healthy.model.vo.bed.SysBuildingInfoQueryVO>
     */
    @Override
    public List<SysBuildingInfoQueryVO> querySysBuildingInfoGetList(UserInfoToken userInfoToken) {
        Example example = new Example(SysBuildingInfo.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("isDel", 0);
        example.orderBy("buildingCode");
        List<SysBuildingInfo> sysBuildingInfoList = sysBuildingInfoMapper.selectByExample(example);
        if(CollectionUtil.isEmpty(sysBuildingInfoList)) {
            return new ArrayList<>();
        }
        return BeanHelper.copyWithCollection(sysBuildingInfoList, SysBuildingInfoQueryVO.class);
    }

    /**
     * 获取楼层下拉列表
     * @author linxiazhu
     * @date 9:37 2021/6/29
     * @param buildingCode  楼宇code
     * @param userInfoToken   token
     * @return  java.util.List<com.nurse.healthy.model.vo.bed.SysFloorInfoQueryVO>
     */
    @Override
    public List<SysFloorInfoQueryVO> querySysFloorInfoGetList(String buildingCode, UserInfoToken userInfoToken) {
        Example example = new Example(SysFloorInfo.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("isDel", 0);
        criteria.andEqualTo("buildingCode", buildingCode);
        example.orderBy("floorCode");
        List<SysFloorInfo> sysFloorInfoList = sysFloorInfoMapper.selectByExample(example);
        if(CollectionUtil.isEmpty(sysFloorInfoList)) {
            return new ArrayList<>();
        }
        return BeanHelper.copyWithCollection(sysFloorInfoList, SysFloorInfoQueryVO.class);
    }

    /**
     * 获取房间下拉列表
     * @author linxiazhu
     * @date 9:43 2021/6/29
     * @param sysRoomInfoGetListPO  入参
     * @param userInfoToken   token
     * @return  java.util.List<com.nurse.healthy.model.vo.bed.SysRoomInfoGetListVO>
     */
    @Override
    public List<SysRoomInfoGetListVO> querySysRoomInfoGetList(SysRoomInfoGetListPO sysRoomInfoGetListPO, UserInfoToken userInfoToken) {
        Example example = new Example(SysRoomInfo.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("isDel", 0);
        criteria.andEqualTo("buildingCode", sysRoomInfoGetListPO.getBuildingCode());
        criteria.andEqualTo("floorCode", sysRoomInfoGetListPO.getFloorCode());
        example.orderBy("roomCode");
        List<SysRoomInfo> sysRoomInfoList = sysRoomInfoMapper.selectByExample(example);
        if(CollectionUtil.isEmpty(sysRoomInfoList)) {
            return new ArrayList<>();
        }
        return BeanHelper.copyWithCollection(sysRoomInfoList, SysRoomInfoGetListVO.class);
    }

    /**
     * 床位tree
     * @author linxiazhu
     * @date 13:29 2021/6/29
     * @param userInfoToken   token
     * @return  java.util.List<cn.hutool.core.lang.tree.Tree<java.lang.String>>
     */
    @Override
    public List<Tree<String>> queryBedTree(UserInfoToken userInfoToken) {
        List<BedTreeVO> bedTreeVOList = sysBuildingInfoMapper.queryBedTree();
        TreeNodeConfig config = new TreeNodeConfig();
        config.setIdKey("id");
        config.setParentIdKey("parentId");
        config.setNameKey("name");
        List<Tree<String>> treeList = TreeUtil.build(bedTreeVOList, "0", config, (treeNode, tree) -> {
            tree.setId(treeNode.getId().toString());
            tree.setParentId(treeNode.getParentId().toString());
            tree.setName(treeNode.getName());
            tree.putExtra("parentName", treeNode.getParentName());
        });
        return treeList;
    }
}
