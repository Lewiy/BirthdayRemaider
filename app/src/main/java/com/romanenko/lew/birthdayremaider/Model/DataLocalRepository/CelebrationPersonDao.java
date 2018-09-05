package com.romanenko.lew.birthdayremaider.Model.DataLocalRepository;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.romanenko.lew.birthdayremaider.Model.DataLocalRepository.QueryObjects.ListRequirementData;
import com.romanenko.lew.birthdayremaider.Model.DataLocalRepository.QueryObjects.PersonalPageRequirementData;

import java.util.List;

import io.reactivex.Flowable;

@Dao
public interface CelebrationPersonDao {

    // @Query("SELECT * FROM celebration_person")
    //List<CelebrationPersonEntity> getAll();

    @Query("SELECT first_name,last_name,date,foto_path FROM celebration_person")
    Flowable<List<ListRequirementData>> getListCelebration();

    @Query("SELECT comment,type_celebration from celebration_person WHERE _id = :userId ")
    Flowable<PersonalPageRequirementData> getPersonalPage(String userId);

    @Insert
    void birthdayPersonInsert(CelebrationPersonEntity celebrationPersonEntity);

    @Update
    void birthdayPersonUpdete(CelebrationPersonEntity celebrationPersonEntity);

    @Delete
    void birthdayPersonDelete(CelebrationPersonEntity celebrationPersonEntity);


}
