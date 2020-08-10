package com.phonepe.cabmanagement.repository.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Repository;

import com.phonepe.cabmanagement.model.Cab;
import com.phonepe.cabmanagement.repository.CabHistoryRepository;

@Repository
public class CabHistoryRepositoryImpl implements CabHistoryRepository {

	Map<String, List<Cab>> cabHistrory = new HashMap<>();

	public void add(Cab cab) {
		Cab copiedBean = new Cab();
		BeanUtils.copyProperties(cab, copiedBean);
		cabHistrory.putIfAbsent(copiedBean.getId(), new ArrayList<Cab>());
		cabHistrory.get(copiedBean.getId()).add(copiedBean);
	}

	@Override
	public List<Cab> get(String cabId) {
		return cabHistrory.get(cabId);
	}
}
