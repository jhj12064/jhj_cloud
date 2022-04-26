package com.jhj.oss.dao;

import com.jhj.oss.model.entity.FileTest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileDao extends JpaRepository<FileTest, Long> {
}
