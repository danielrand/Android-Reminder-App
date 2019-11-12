package edu.qc.seclass.glm;

import android.app.Application;
import android.os.AsyncTask;

import java.util.List;

import androidx.lifecycle.LiveData;

class ReminderRepository {

    private ReminderDao mWordDao;
    private LiveData<List<ReminderEntity>> mAllWords;

    // Note that in order to unit test the WordRepository, you have to remove the Application
    // dependency. This adds complexity and much more code, and this sample is not about testing.
    // See the BasicSample in the android-architecture-components repository at
    // https://github.com/googlesamples
    ReminderRepository(Application application) {
        ReminderDatabase db = ReminderDatabase.getDatabase(application);
        mWordDao = db.reminderDao();
        mAllWords = mWordDao.getAll();
    }

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    LiveData<List<ReminderEntity>> getAllWords() {
        return mAllWords;
    }

    // You must call this on a non-UI thread or your app will crash.
    // Like this, Room ensures that you're not doing any long running operations on the main
    // thread, blocking the UI.
    void insert(ReminderEntity word) {
        new insertAsyncTask(mWordDao).execute(word);
    }

    ReminderEntity getReminder(String desc) {
        return mWordDao.findByName(desc);
    }

    private static class insertAsyncTask extends AsyncTask<ReminderEntity, Void, Void> {

        private ReminderDao mAsyncTaskDao;

        insertAsyncTask(ReminderDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final ReminderEntity... params) {
            mAsyncTaskDao.insertAll(params[0]);
            return null;
        }
    }
}
