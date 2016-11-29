package core.ui;

import core.kernel.DefaultGuiBuilder;
import core.kernel.GuiLoader;
import core.kernel.LoadingFailedException;
import core.kernel.LoadingWeight;

/**
 * Installs various cell renderers on a {@link RendererFactory}, and attaches that factory to the builder.
 * @author Timmos
 */
public class DefaultRenderFactoryLoader implements GuiLoader {

    @Override
    public void load(DefaultGuiBuilder builder) throws LoadingFailedException {
        RendererFactoryBuilder rendFactBuilder = new DefaultRendererFactory.Builder();

        addRenderers(rendFactBuilder);

        RendererFactory factory = rendFactBuilder.build();
        builder.setRenderFactory(factory);
    }

    /**
     * Attaches renderers to the renderer factory builder. (subclasses are thus not required to override
     * {@link #load(DefaultGuiBuilder)}, except if they want to install a different renderer factory).
     * @param builder the renderer factory builder
     */
    protected void addRenderers(RendererFactoryBuilder builder) {
        // no renderers
    }

    @Override
    public LoadingWeight getWeight() {
        return LoadingWeight.MEDIUM;
    }

    @Override
    public String getInternalName() {
        return "Renderers";
    }
}
