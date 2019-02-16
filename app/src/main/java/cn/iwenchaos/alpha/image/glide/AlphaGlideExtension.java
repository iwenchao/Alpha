package cn.iwenchaos.alpha.image.glide;

import com.bumptech.glide.annotation.GlideExtension;
import com.bumptech.glide.load.resource.gif.GifDrawable;
import com.bumptech.glide.request.RequestOptions;

import static com.bumptech.glide.request.RequestOptions.decodeTypeOf;


/**
 * Created by chaos
 * on 2019/2/4. 12:05
 * 文件描述：
 */
@GlideExtension
public final class AlphaGlideExtension {


    private AlphaGlideExtension() {
    }

    private static final RequestOptions DECODE_TYPE_GIF = decodeTypeOf(GifDrawable.class).lock();




}
