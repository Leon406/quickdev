package ll.leon.com.widget_animation_effect.animation.rx2anim;

import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;

import java.util.concurrent.TimeUnit;

import io.reactivex.Completable;
import io.reactivex.CompletableSource;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Action;

import static ll.leon.com.widget_animation_effect.animation.rx2anim.RxAnimationBuilder.animate;


public final class RxAnimations {

    private static final float OPAQUE = 1.0f;
    private static final float TRANSPARENT = 0.0f;

    private static final int IMMEDIATE = 0;

    public static Completable animateTogether(final CompletableSource... completables) {
        return Completable.mergeArray(completables);
    }

    public static Completable hide(final View view) {
        return animate(view, IMMEDIATE).fadeOut().schedule();
    }

    public static Completable hide(final View... views) {
        return Observable.fromArray(views)
                         .flatMapCompletable(RxAnimations::hide);
    }

    public static Completable hideViewGroupChildren(final ViewGroup viewGroup) {
        return Completable.fromAction(() -> hideViewGroup(viewGroup));
    }

    public static Completable hideViewGroupChildren(final ViewGroup... viewGroups) {
        return Observable.fromArray(viewGroups)
                         .flatMapCompletable(RxAnimations::hideViewGroupChildren);
    }

    private static void hideViewGroup(final ViewGroup viewGroup) {
        for (int i = 0, childCount = viewGroup.getChildCount(); i < childCount; i++) {
            final View child = viewGroup.getChildAt(i);
            if (child instanceof ViewGroup) {
                hideViewGroup((ViewGroup) child);
            } else {
                child.setAlpha(0f);
            }
        }
    }

    public static Completable show(final View view) {
        return animate(view, IMMEDIATE).fadeIn().schedule();
    }

    public static Completable fadeIn(final View view) {
        return animate(view).fadeIn()
                            .onAnimationCancel(aView -> aView.setAlpha(OPAQUE))
                            .schedule();
    }

    public static Completable fadeIn(final View view, final int duration) {
        return animate(view, new DecelerateInterpolator())
                .duration(duration)
                .fadeIn()
                .onAnimationCancel(aView -> aView.setAlpha(OPAQUE))
                .schedule();
    }

    public static Completable fadeIn(final View view, final int duration, final int delay) {
        return animate(view, duration, delay)
                .interpolator(new DecelerateInterpolator())
                .fadeIn()
                .onAnimationCancel(aView -> aView.setAlpha(OPAQUE))
                .schedule();
    }

    public static Completable fadeInWithDelay(final int delay, final int duration, final View... views) {
        return Observable.range(0, views.length)
                         .flatMapCompletable(i -> animate(views[i], new LinearInterpolator())
                                 .duration(duration)
                                 .delay(i * delay)
                                 .fadeIn().schedule());
    }

    public static Completable slideHorizontal(final View view, final int duration, final int xOffset) {
        final float endingX = view.getX() + xOffset;
        return animate(view, new AccelerateDecelerateInterpolator())
                .duration(duration)
                .translateBy(xOffset, 0)
                .onAnimationCancel(v -> v.setX(endingX))
                .schedule(false);
    }

    public static Completable slideVertical(final View view, final int duration, final int yOffset) {
        final float endingY = view.getY() + yOffset;
        return animate(view, new AccelerateDecelerateInterpolator())
                .duration(duration)
                .translateBy(0, yOffset)
                .onAnimationCancel(v -> v.setY(endingY))
                .schedule(false);
    }

    public static Completable enter(final View view, final int xOffset, final int yOffset) {
        final float startingX = view.getX();
        final float startingY = view.getY();
        return animate(view, new DecelerateInterpolator())
                .fadeIn()
                .translateBy(xOffset, yOffset)
                .onAnimationCancel(aView -> set(aView, startingX, startingY, OPAQUE))
                .schedule();
    }

    public static Completable enter(final View view, final int delay, final int xOffset, final int yOffset) {
        final float startingX = view.getX();
        final float startingY = view.getY();
        return animate(view, new DecelerateInterpolator())
                .delay(delay)
                .fadeIn()
                .translateBy(xOffset, yOffset)
                .onAnimationCancel(aView -> set(aView, startingX, startingY, OPAQUE))
                .schedule();
    }

    public static Completable enter(final View view, final int duration, final int xOffset, final int yOffset, final int delay) {
        final float startingX = view.getX();
        final float startingY = view.getY();
        return animate(view, duration, delay)
                .interpolator(new DecelerateInterpolator())
                .fadeIn()
                .translateBy(xOffset, yOffset)
                .onAnimationCancel(aView -> set(aView, startingX, startingY, OPAQUE))
                .schedule();
    }

    public static Completable enterTogether(final int xOffset, final View... views) {
        return Observable.fromArray(views)
                         .flatMapCompletable(view -> enter(view, xOffset, 0));
    }

    public static Completable enterTogether(final int delay, final int xOffset, final View... views) {
        return doAfterDelay(delay, Observable.fromArray(views)
                                             .flatMapCompletable(view -> enter(view, xOffset, 0)));
    }

    public static Completable enterViewsWithDelay(final int delay, final int duration, final int xOffset, final View... views) {
        return enterViewsWithDelay(0, delay, duration, xOffset, views);
    }

    public static Completable enterViewsWithDelay(final int initialDelay, final int delay, final int duration, final int xOffset, final View... views) {
        return Observable.range(0, views.length)
                         .flatMapCompletable(i -> enter(views[i], duration, xOffset, 0, i * delay + initialDelay));
    }

    public static Completable enterWithRotation(final View view, final int duration, final int xOffset, final int yOffset, final int delay, final int rotation) {
        final float startingX = view.getX();
        final float startingY = view.getY();
        final float startRotation = view.getRotation();
        return animate(view, duration, delay)
                .fadeIn()
                .counterRotateBy(rotation)
                .translateBy(xOffset, yOffset)
                .onAnimationCancel(aView -> set(aView, startingX, startingY, OPAQUE, startRotation))
                .schedule();
    }

    public static Completable leave(final View view, final int xOffset, final int yOffset) {
        final float startingX = view.getX();
        final float startingY = view.getY();
        return animate(view, new AccelerateInterpolator())
                .fadeOut()
                .translateBy(xOffset, yOffset)
                .onAnimationCancel(aView -> set(aView, startingX, startingY, TRANSPARENT))
                .schedule(false);
    }

    public static Completable fadeOut(final View view) {
        return animate(view, new AccelerateInterpolator())
                .fadeOut()
                .onAnimationCancel(aView -> aView.setAlpha(TRANSPARENT))
                .schedule();
    }

    public static Completable fadeOut(final View view, final int duration) {
        return animate(view, new AccelerateInterpolator())
                .duration(duration)
                .fadeOut()
                .onAnimationCancel(aView -> aView.setAlpha(TRANSPARENT))
                .schedule();
    }

    public static Completable fadeOut(final View view, final int duration, final int delay) {
        return animate(view, duration, delay)
                .interpolator(new AccelerateInterpolator())
                .fadeOut()
                .onAnimationCancel(aView -> aView.setAlpha(TRANSPARENT))
                .schedule();
    }

    public static Completable doAfterDelay(final int delay, final Action action) {
        return Completable.timer(delay, TimeUnit.MILLISECONDS)
                          .observeOn(AndroidSchedulers.mainThread())
                          .concatWith(Completable.fromAction(action));
    }

    public static Completable doAfterDelay(final int delay, final Completable completable) {
        return Completable.timer(delay, TimeUnit.MILLISECONDS)
                          .observeOn(AndroidSchedulers.mainThread())
                          .concatWith(completable);
    }

    public static void set(final View view, final float x, final float y, final float alpha) {
        view.setAlpha(alpha);
        view.setX(x);
        view.setY(y);
    }

    public static void set(final View view, final float x, final float y, final float alpha, final float rotation) {
        set(view, x, y, alpha);
        view.setRotation(rotation);
    }
}
