package com.zereao.freemarker.dao;

import com.zereao.freemarker.po.Message;
import com.zereao.freemarker.vo.TypeDetailVO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * @author Darion Mograine H
 * @version 2019/03/26  11:41
 */
@Repository
public interface MessageDAO extends JpaRepository<Message, Long> {
    @Query("   SELECT msg.productSystem " +
            "    FROM Message msg " +
            "   WHERE msg.createTime BETWEEN :start AND :end " +
            "GROUP BY msg.productSystem")
    List<String> findAllSystem(Date start, Date end);

    @SuppressWarnings("SpringDataRepositoryMethodReturnTypeInspection")
    @Query("   SELECT new com.zereao.freemarker.vo.TypeDetailVO(msg.sentType, COUNT(msg.id), " +
            "   SUM(CASE WHEN msg.statusCode = 'SEND_SUCCESS' THEN 1 ELSE 0 END), " +
            "   SUM(CASE WHEN msg.statusCode = 'SEND_FAILED' THEN 1 ELSE 0 END), " +
            "   SUM(CASE WHEN msg.statusCode = 'READY_SEND' THEN 1 ELSE 0 END), " +
            "   SUM(CASE WHEN (msg.updateTime - msg.createTime < 10000) THEN 1 ELSE 0 END), " +
            "   SUM(CASE WHEN (msg.updateTime - msg.createTime > 10000 AND msg.updateTime - msg.createTime < 30000) THEN 1 ELSE 0 END), " +
            "   SUM(CASE WHEN (msg.updateTime - msg.createTime > 30000 AND msg.updateTime - msg.createTime < 60000) THEN 1 ELSE 0 END), " +
            "   SUM(CASE WHEN (msg.updateTime - msg.createTime > 60000 AND msg.updateTime - msg.createTime < 600000) THEN 1 ELSE 0 END), " +
            "   SUM(CASE WHEN (msg.updateTime - msg.createTime > 600000) THEN 1 ELSE 0 END) )" +
            "    FROM Message msg " +
            "   WHERE msg.productSystem = :system " +
            "     AND msg.createTime BETWEEN :start AND :endTime " +
            "GROUP BY msg.sentType")
    List<TypeDetailVO> findCountBySystem(@Param("system") String system, @Param("start") Date start, @Param("endTime") Date endTime);
}
