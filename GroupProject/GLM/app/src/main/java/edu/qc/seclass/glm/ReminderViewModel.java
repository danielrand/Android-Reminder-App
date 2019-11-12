package edu.qc.seclass.glm;

import android.app.Application;

import java.util.List;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class ReminderViewModel extends AndroidViewModel {

    private ReminderRepository mRepository;
    // Using LiveData and caching what getAlphabetizedWords returns has several benefits:
    // - We can put an observer on the data (instead of polling for changes) and only update the
    //   the UI when the data actually changes.
    // - Repository is completely separated from the UI through the ViewModel.
    private LiveData<List<ReminderEntity>> mAllWords;

    public ReminderViewModel(Application application) {
        super(application);
        mRepository = new ReminderRepository(application);
        mAllWords = mRepository.getAllWords();
    }

    LiveData<List<ReminderEntity>> getAllWords() {
        return mAllWords;
    }

    void insert(ReminderEntity word) {
        mRepository.insert(word);
    }

    ReminderEntity getReminder(String desc){
        return mRepository.getReminder(desc);
    }
}
