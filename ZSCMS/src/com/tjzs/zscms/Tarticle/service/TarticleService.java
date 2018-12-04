package com.tjzs.zscms.Tarticle.service;

import java.util.List;

import com.tjzs.zscms.Tarticle.bean.TarticleBean;
import com.tjzs.zscms.Tarticle.dao.impl.TarticleDaoImpl;
import com.tjzs.zscms.exception.BusinessException;
import com.tjzs.zscms.exception.SysException;

public class TarticleService {
	/**
	 * 用户的业务逻辑处理层
	 * */
	// 采用多态的形式
	TarticleDaoImpl ad = new TarticleDaoImpl();

	// 查询所有文章信息
	/**
	 * page表示查询的当前页数 size：条数
	 * */
	public List<TarticleBean> queryByPage(int page, int size) throws SysException {
		int start = (page - 1) * size;
		return ad.queryByPage(start, size);

	}

	// 新增文章
	/*
	 * 判断标题是否重复 如果重复，抛出异常，然后将异常信息放到页面上面 1成功 0失败
	 */
	public int insertTarticle(TarticleBean tar) throws SysException, BusinessException {
		List<TarticleBean> tar1 = ad.queryByTitle(tar.getTitle());
		if (tar1 != null && tar1.size() != 0) {// 标题重复
			throw new BusinessException("标题重复，请重新输入", 101);
		}
		return ad.insertTarticle(tar);
	}

	// 根据id查询文章信息
	public TarticleBean queryTarticleById(int id) throws SysException {
		List<TarticleBean> tars = ad.queryTarticleById(id);
		return tars.get(0);

	}

	// 修改文章信息
	public int updateTarticle(TarticleBean tar) throws SysException {
		int tar1 = ad.updateTarticle(tar);
		return tar1;

	}

	// 删除文章信息
	public int delectTarticle(int id) throws SysException {
		int tar = ad.delectTarticle(id);
		return tar;
	}
	/* 查询总页数 */
	public int queryPageCount(int size) throws SysException {
		// 将用户的总记录转换为总页数
		int TarticleCount = ad.queryTarticleCounts();
		if (TarticleCount % size == 0) {// 表示总页数能够整除每页查询的数据量
			// 总页数
			return TarticleCount / size;
		} else {
			return TarticleCount / size + 1;
		}
	}
}

