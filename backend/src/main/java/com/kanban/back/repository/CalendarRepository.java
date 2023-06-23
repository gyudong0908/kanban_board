package com.kanban.back.repository;

import com.kanban.back.entity.Calendar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CalendarRepository extends JpaRepository<Calendar, Integer> {
    @Query("select c from Calendar c where month(c.cal_date) = :month")
    List<Calendar> getCalendarsByMonth(@Param("month") String month);

    @Procedure(procedureName = "restore_calendar")
    void restoreCalendar(@Param(value = "cal_id") Integer cal_id);
}
