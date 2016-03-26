package ph.intrepidstream.tycoons.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import ph.intrepidstream.tycoons.Tycoons;

public abstract class AbstractScreen implements Screen {

    public static final int VIEWPORT_WIDTH = 1080;
    public static final int VIEWPORT_HEIGHT = 1920;
    protected final String TAG = getClass().getSimpleName();

    protected final Tycoons game;
    protected OrthographicCamera camera;
    protected Stage stage;

    public AbstractScreen(final Tycoons game) {
        this.game = game;

        camera = new OrthographicCamera();
        camera.setToOrtho(false, VIEWPORT_WIDTH, VIEWPORT_HEIGHT);

        stage = new Stage(new StretchViewport(VIEWPORT_WIDTH, VIEWPORT_HEIGHT, camera), game.getBatch());
        stage.setDebugAll(Tycoons.DEV_MODE);

        initActors();
    }

    /**
     * Initialization of stage actors
     */
    protected abstract void initActors();

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
        Gdx.app.log(TAG, "Resizing screen");
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);

        camera.update();

        stage.act(delta);
        stage.draw();
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
        Gdx.app.log(TAG, "Showing screen");
    }

    @Override
    public void dispose() {
        stage.dispose();
        Gdx.app.log(TAG, "Disposing screen");
    }

    @Override
    public void pause() {
        Gdx.app.log(TAG, "Pausing screen");
    }

    @Override
    public void resume() {
        Gdx.app.log(TAG, "Resuming screen");
    }

    @Override
    public void hide() {
        Gdx.app.log(TAG, "Hiding screen");
    }

}
