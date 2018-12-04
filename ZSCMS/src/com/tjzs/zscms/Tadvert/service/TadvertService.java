package com.tjzs.zscms.Tadvert.service;

import java.util.List;

import com.tjzs.zscms.Tadvert.bean.TadvertBean;
import com.tjzs.zscms.Tadvert.dao.impl.TadvertDaoImpl;
import com.tjzs.zscms.exception.BusinessException;
import com.tjzs.zscms.exception.SysException;

public class TadvertService {
	/**
	 * 用户的业务逻辑处理层
	 * */
	// 采用多态的形式
	TadvertDaoImpl ad = new TadvertDaoImpl();

	// 查询所有广告信息
	/**
	 * page表示查询的当前页数 size：条数
	 * */
	public List<TadvertBean> queryByPage(int page, int size) throws SysException {
		int start = (page - 1) * size;
		return ad.queryByPage(start, size);

	}

	// 新增用户
	/*
	 * 判断标题是否重复 如果重复，抛出异常，然后将异常信息放到页面上面 1成功 0失败
	 */
	public int insertTadvert(TadvertBean tad) throws SysException, BusinessException {
		List<TadvertBean> tad1 = ad.queryByTitle(tad.getTitle());
		if (tad1 != null && tad1.size() != 0) {// 标题重复
			throw new BusinessException("标题重复，请重新输入", 101);
		}
		return ad.insertTadvert(tad);
	}

	// 根据id查询广告信息
	public TadvertBean queryTadvertById(int id) throws SysException {
		List<TadvertBean> tads = ad.queryTadvertById(id);
		return tads.get(0);

	}

	// 修改广告信息
	public int updateTadvert(TadvertBean tad) throws SysException {
		int tad1 = ad.updateTadvert(tad);
		return tad1;

	}

	// 删除广告信息
	public int delectTadvert(int id) throws SysException {
		int tad = ad.delectTadvert(id);
		return tad;
	}
	/* 查询总页数 */
	public int queryPageCount(int size) throws SysException {
		// 将用户的总记录转换为总页数
		int TadvertCount = ad.queryTadvertCounts();
		if (TadvertCount % size == 0) {// 表示总页数能够整除每页查询的数据量
			// 总页数
			return TadvertCount / size;
		} else {
			return TadvertCount / size + 1;
		}
	}
}
