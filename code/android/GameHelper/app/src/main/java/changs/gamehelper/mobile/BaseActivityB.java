package changs.gamehelper.mobile;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;

import changs.android.frame.components.FActivity;

/**
 * Created by yincs on 2017/2/25.
 */

public abstract class BaseActivityB<B extends ViewDataBinding> extends FActivity {

    protected B binding;

    @Override
    public void setRootView() {
        final int layoutRes = getLayoutRes();
        if (layoutRes != 0)
            binding = DataBindingUtil.setContentView(this, layoutRes);
    }
}
