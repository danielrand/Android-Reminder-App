package edu.qc.seclass.glm;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

@Dao
public interface ReminderDao {

    @Query("SELECT * FROM reminder_table")
    LiveData<List<ReminderEntity>> getAll();

    @Query("SELECT * FROM reminder_table WHERE reminder_id IN (:reminderIDs)")
    LiveData<List<ReminderEntity>> loadAllByIds(int[] reminderIDs);

    @Query("SELECT * FROM reminder_table WHERE description LIKE :desc LIMIT 1")
    ReminderEntity findByName(String desc);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(ReminderEntity ... reminders);

    @Delete
    void delete(ReminderEntity reminder);

}