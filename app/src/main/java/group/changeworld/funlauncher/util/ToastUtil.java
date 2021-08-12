package group.changeworld.funlauncher.util;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by Administrator on 2/24 0024.
 */

public class ToastUtil extends Toast {
    public ToastUtil(Context context){
        super(context);
    };
    public static void show(Context context,String sMessage){
        Toast.makeText(context,sMessage,Toast.LENGTH_SHORT).show();
    }

}
