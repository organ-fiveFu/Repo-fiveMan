package com.vblessings.nhs.service;

import cn.hutool.core.lang.tree.Tree;
import com.vblessings.nhs.model.po.bed.*;
import com.vblessings.nhs.model.vo.PageVO;
import com.vblessings.nhs.model.vo.bed.*;
import com.vblessings.nhs.result.UserInfoToken;
import com.vblessings.nhs.model.po.bed.*;
import com.vblessings.nhs.model.vo.bed.*;

import java.util.List;

public interface BedService {
    /**
     * 新增楼宇信息
     * @author linxiazhu
     * @date 17:08 2021/6/24
     * @param sysBuildingInfoInsertPO  入参
     * @param userInfoToken   token
     * @return  boolean
     */
    boolean insertSysBuildingInfo(SysBuildingInfoInsertPO sysBuildingInfoInsertPO, UserInfoToken userInfoToken);

    /**
     * 更新楼宇信息
     * @author linxiazhu
     * @date 16:38 2021/6/25
     * @param sysBuildingInfoUpdatePO  入参
     * @param userInfoToken   token
     * @return  boolean
     */
    boolean updateSysBuildingInfo(SysBuildingInfoUpdatePO sysBuildingInfoUpdatePO, UserInfoToken userInfoToken);

    /**
     * 查询楼宇信息
     * @author linxiazhu
     * @date 17:28 2021/6/25
     * @param sysBuildingInfoQueryPO  入参
     * @param userInfoToken   token
     * @return  com.vblessings.nhs.model.vo.PageVO<com.vblessings.nhs.model.vo.bed.SysBuildingInfoQueryVO>
     */
    PageVO<SysBuildingInfoQueryVO> querySysBuildingInfoList(SysBuildingInfoQueryPO sysBuildingInfoQueryPO, UserInfoToken userInfoToken);

    /**
     * 删除楼宇信息
     * @author linxiazhu
     * @date 13:40 2021/6/27
     * @param id id
     * @param userInfoToken   token
     * @return  boolean
     */
    boolean deleteSysBuildingInfo(Long id, UserInfoToken userInfoToken);

    /**
     * 新增楼层信息
     * @author linxiazhu
     * @date 13:58 2021/6/27
     * @param sysFloorInfoInsertPO  入参
     * @param userInfoToken   token
     * @return  boolean
     */
    boolean insertSysFloorInfo(SysFloorInfoInsertPO sysFloorInfoInsertPO, UserInfoToken userInfoToken);

    /**
     * 更新楼层信息
     * @author linxiazhu
     * @date 14:09 2021/6/27
     * @param sysFloorInfoUpdatePO  入参
     * @param userInfoToken   token
     * @return  boolean
     */
    boolean updateSysFloorInfo(SysFloorInfoUpdatePO sysFloorInfoUpdatePO, UserInfoToken userInfoToken);

    /**
     * 查询楼层信息
     * @author linxiazhu
     * @date 14:25 2021/6/27
     * @param sysFloorInfoQueryPO  入参
     * @param userInfoToken   token
     * @return  com.vblessings.nhs.model.vo.PageVO<com.vblessings.nhs.model.vo.bed.SysFloorInfoQueryVO>
     */
    PageVO<SysFloorInfoQueryVO> querySysFloorInfoList(SysFloorInfoQueryPO sysFloorInfoQueryPO, UserInfoToken userInfoToken);

    /**
     * 删除楼层信息
     * @author linxiazhu
     * @date 14:40 2021/6/27
     * @param id  id
     * @param userInfoToken   token
     * @return  boolean
     */
    boolean deleteSysFloorInfo(Long id, UserInfoToken userInfoToken);

    /**
     * 新增房间信息
     * @author linxiazhu
     * @date 10:19 2021/6/28
     * @param sysRoomInfoInsertPO  入参
     * @param userInfoToken   token
     * @return  boolean
     */
    boolean insertSysRoomInfo(SysRoomInfoInsertPO sysRoomInfoInsertPO, UserInfoToken userInfoToken);

    /**
     * 更新房间信息
     * @author linxiazhu
     * @date 11:23 2021/6/28
     * @param sysRoomInfoUpdatePO  入参
     * @param userInfoToken   token
     * @return  boolean
     */
    boolean updateSysRoomInfo(SysRoomInfoUpdatePO sysRoomInfoUpdatePO, UserInfoToken userInfoToken);

    /**
     * 查询房间信息
     * @author linxiazhu
     * @date 13:47 2021/6/28
     * @param sysRoomInfoQueryPO  入参
     * @param userInfoToken   token
     * @return  com.vblessings.nhs.model.vo.PageVO<com.vblessings.nhs.model.vo.bed.SysRoomInfoQueryVO>
     */
    PageVO<SysRoomInfoQueryVO> querySysRoomInfoList(SysRoomInfoQueryPO sysRoomInfoQueryPO, UserInfoToken userInfoToken);

    /**
     * 删除房间信息
     * @author linxiazhu
     * @date 16:16 2021/6/28
     * @param id  id
     * @param userInfoToken   token
     * @return  boolean
     */
    boolean deleteSysRoomInfo(Long id, UserInfoToken userInfoToken);

    /**
     * 新增床位信息
     * @author linxiazhu
     * @date 16:49 2021/6/28
     * @param sysBedInfoInsertPO  入参
     * @param userInfoToken   token
     * @return  boolean
     */
    boolean insertSysBedInfo(SysBedInfoInsertPO sysBedInfoInsertPO, UserInfoToken userInfoToken);

    /**
     * 更新床位信息
     * @author linxiazhu
     * @date 17:03 2021/6/28
     * @param sysBedInfoUpdatePO  入参
     * @param userInfoToken   token
     * @return  boolean
     */
    boolean updateSysBedInfo(SysBedInfoUpdatePO sysBedInfoUpdatePO, UserInfoToken userInfoToken);

    /**
     * 查询床位信息
     * @author linxiazhu
     * @date 17:10 2021/6/28
     * @param sysBedInfoQueryPO  入参
     * @param userInfoToken   token
     * @return  com.vblessings.nhs.model.vo.PageVO<com.vblessings.nhs.model.vo.bed.SysBedInfoQueryVO>
     */
    PageVO<SysBedInfoQueryVO> querySysBedInfoList(SysBedInfoQueryPO sysBedInfoQueryPO, UserInfoToken userInfoToken);

    /**
     * 删除床位信息
     * @author linxiazhu
     * @date 17:24 2021/6/28
     * @param id  id
     * @param userInfoToken   token
     * @return  boolean
     */
    boolean deleteSysBedInfo(Long id, UserInfoToken userInfoToken);

    /**
     * 获取楼宇下拉列表
     * @author linxiazhu
     * @date 9:29 2021/6/29
     * @param userInfoToken   token
     * @return  java.util.List<com.nurse.healthy.model.vo.bed.SysBuildingInfoQueryVO>
     */
    List<SysBuildingInfoQueryVO> querySysBuildingInfoGetList(UserInfoToken userInfoToken);
    
    /**
     * 获取楼层下拉列表
     * @author linxiazhu
     * @date 9:35 2021/6/29 
     * @param buildingCode  楼宇code
     * @param userInfoToken   token
     * @return  java.util.List<com.nurse.healthy.model.vo.bed.SysFloorInfoQueryVO>
     */
    List<SysFloorInfoQueryVO> querySysFloorInfoGetList(String buildingCode, UserInfoToken userInfoToken);

    /**
     * 获取房间下拉列表
     * @author linxiazhu
     * @date 9:42 2021/6/29
     * @param sysRoomInfoGetListPO  入参
     * @param userInfoToken   token
     * @return  java.util.List<com.nurse.healthy.model.vo.bed.SysRoomInfoGetListVO>
     */
    List<SysRoomInfoGetListVO> querySysRoomInfoGetList(SysRoomInfoGetListPO sysRoomInfoGetListPO, UserInfoToken userInfoToken);

    /**
     * 床位tree
     * @author linxiazhu
     * @date 13:28 2021/6/29
     * @param userInfoToken   token
     * @return  java.util.List<cn.hutool.core.lang.tree.Tree<java.lang.String>>
     */
    List<Tree<String>> queryBedTree(UserInfoToken userInfoToken);
}
