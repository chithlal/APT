package com.developer.chithlal.apt;

import android.content.Context;

import com.developer.chithlal.apt.factslist.activities.FactListActivity;

import androidx.recyclerview.widget.RecyclerView;
import androidx.test.platform.app.InstrumentationRegistry;
import  androidx.test.espresso.Espresso;
import androidx.test.espresso.assertion.*;
import  androidx.test.espresso.matcher.*;
import  androidx.test.espresso.action.*;
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;
import androidx.test.rule.ActivityTestRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;


import static org.junit.Assert.*;


/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4ClassRunner.class)
public class FactListActivityTest {
    @Rule
    public final ActivityTestRule<FactListActivity> mActivityRule = new ActivityTestRule<>(FactListActivity.class);

    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getContext();

        assertEquals("com.developer.chithlal.apt", appContext.getPackageName());
    }
    @Test
    public void testRecyclerView() throws InterruptedException {
        int itemCount=0;
        RecyclerView recyclerView = mActivityRule.getActivity().findViewById(R.id.rv_facts);
        Thread.sleep(2000);
        if (recyclerView.getAdapter()!=null)
     itemCount =recyclerView.getAdapter().getItemCount();
    if(itemCount>0)
    {
        Espresso.onView(ViewMatchers.withId(R.id.rv_facts)).perform(ViewActions.click()).check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
    }
    }
    @Test
    public void testRecyclerViewError() throws InterruptedException {
        RecyclerView recyclerView = mActivityRule.getActivity().findViewById(R.id.rv_facts);
        Thread.sleep(2000);
        int itemCount=0;
        if (recyclerView.getAdapter()!=null)
         itemCount =recyclerView.getAdapter().getItemCount();
        if(itemCount>0)
        {
            Espresso.onView(ViewMatchers.withId(R.id.rv_facts)).perform(ViewActions.click()).check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
        }
    }
}
