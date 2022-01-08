package com.example.pettyplanet.data.local


import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.example.pettyplanet.daos.SavedPostsDao
import com.example.pettyplanet.database.SavedPostsDatabase
import com.example.pettyplanet.models.SavedPosts
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith


@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
@SmallTest
class SavedPostsDaoTests {


    private lateinit var database: SavedPostsDatabase
    private lateinit var dao: SavedPostsDao

    @Before
    fun setup() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            SavedPostsDatabase::class.java
        ).allowMainThreadQueries().build()
        dao = database.savedPostDao
    }

    @After
    fun teardown() {
        database.close()
    }

    @Test
    fun insertPostsItem() = runBlocking {
        val post = SavedPosts(text = "TEST", "user", 123098994098, "url", "url", 0)
        dao.insertPost(post)

        val allposts = dao.getPosts()

        assertThat(allposts)
            .contains(post)
    }

    @Test
    fun deletePostItem() = runBlocking {
        val post = SavedPosts("TESTer", "userer", 1230986994098, "urlr", "urler", 2)
        dao.insertPost(post)
        dao.deletePost(post)

        val allPosts = dao.getPosts()

        assertThat(allPosts).doesNotContain(post)
    }


}