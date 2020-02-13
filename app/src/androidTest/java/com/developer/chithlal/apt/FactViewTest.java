package com.developer.chithlal.apt;

import android.content.Context;

import com.developer.chithlal.apt.Actvities.FactView;
import com.developer.chithlal.apt.Models.Fact;

import androidx.recyclerview.widget.RecyclerView;
import androidx.test.InstrumentationRegistry;
import  androidx.test.espresso.Espresso;
import androidx.test.espresso.ViewAction;
import androidx.test.espresso.assertion.*;
import  androidx.test.espresso.matcher.*;
import  androidx.test.espresso.action.*;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;


import static org.junit.Assert.*;


/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class FactViewTest {
    @Rule
    public final ActivityTestRule<FactView> mActivityRule = new ActivityTestRule<>(FactView.class);

    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("com.developer.chithlal.apt", appContext.getPackageName());
    }
    @Test
    public void testRecyclerView() throws InterruptedException {
        RecyclerView recyclerView = mActivityRule.getActivity().findViewById(R.id.rv_facts);
        Thread.sleep(2000);
    int itemCount =recyclerView.getAdapter().getItemCount();
    if(itemCount>0)
    {
        Espresso.onView(ViewMatchers.withId(R.id.rv_facts)).perform(ViewActions.click()).check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
    }
    }
    @Test
    public void testRecyclerViewError() throws InterruptedException {
        RecyclerView recyclerView = mActivityRule.getActivity().findViewById(R.id.rv_facts);
        Thread.sleep(2000);
        int itemCount =recyclerView.getAdapter().getItemCount();
        if(itemCount>0)
        {
            Espresso.onView(ViewMatchers.withId(R.id.rv_facts)).perform(ViewActions.click()).check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
        }
    }
}
