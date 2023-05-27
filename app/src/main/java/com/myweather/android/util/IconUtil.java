package com.myweather.android.util;

import com.myweather.android.R;

public class IconUtil {

    // 获取白天天气图标
    public static int getDayIcon(String iconDay) {
        int imageId;
        switch (iconDay) {
            case "100":
                imageId = R.mipmap.icon_100d;
                break;
            case "101":
                imageId = R.mipmap.icon_101d;
                break;
            case "102":
                imageId = R.mipmap.icon_102d;
                break;
            case "103":
                imageId = R.mipmap.icon_103d;
                break;
            case "104":
                imageId = R.mipmap.icon_104d;
                break;
            case "200":
                imageId = R.mipmap.icon_200d;
                break;
            case "201":
                imageId = R.mipmap.icon_210d;
                break;
            case "202":
                imageId = R.mipmap.icon_202d;
                break;
            case "203":
                imageId = R.mipmap.icon_203d;
                break;
            case "204":
                imageId = R.mipmap.icon_204d;
                break;
            case "205":
                imageId = R.mipmap.icon_205d;
                break;
            case "206":
                imageId = R.mipmap.icon_206d;
                break;
            case "207":
                imageId = R.mipmap.icon_207d;
                break;
            case "208":
                imageId = R.mipmap.icon_208d;
                break;
            case "209":
                imageId = R.mipmap.icon_209d;
                break;
            case "210":
                imageId = R.mipmap.icon_210d;
                break;
            case "211":
                imageId = R.mipmap.icon_211d;
                break;
            case "212":
                imageId = R.mipmap.icon_212d;
                break;
            case "213":
                imageId = R.mipmap.icon_213d;
                break;
            case "300":
                imageId = R.mipmap.icon_300d;
                break;
            case "301":
                imageId = R.mipmap.icon_301d;
                break;
            case "302":
                imageId = R.mipmap.icon_302d;
                break;
            case "303":
                imageId = R.mipmap.icon_303d;
                break;
            case "304":
                imageId = R.mipmap.icon_304d;
                break;
            case "305":
                imageId = R.mipmap.icon_305d;
                break;
            case "306":
                imageId = R.mipmap.icon_306d;
                break;
            case "307":
                imageId = R.mipmap.icon_307d;
                break;
            case "308":
                imageId = R.mipmap.icon_308d;
                break;
            case "309":
                imageId = R.mipmap.icon_309d;
                break;
            case "310":
                imageId = R.mipmap.icon_310d;
                break;
            case "311":
                imageId = R.mipmap.icon_311d;
                break;
            case "312":
                imageId = R.mipmap.icon_312d;
                break;
            case "313":
                imageId = R.mipmap.icon_313d;
                break;
            case "314":
                imageId = R.mipmap.icon_314d;
                break;
            case "315":
                imageId = R.mipmap.icon_315d;
                break;
            case "316":
                imageId = R.mipmap.icon_316d;
                break;
            case "317":
                imageId = R.mipmap.icon_317d;
                break;
            case "318":
                imageId = R.mipmap.icon_318d;
                break;
            case "399":
                imageId = R.mipmap.icon_399d;
                break;
            case "400":
                imageId = R.mipmap.icon_400d;
                break;
            case "401":
                imageId = R.mipmap.icon_401d;
                break;
            case "402":
                imageId = R.mipmap.icon_402d;
                break;
            case "403":
                imageId = R.mipmap.icon_403d;
                break;
            case "404":
                imageId = R.mipmap.icon_404d;
                break;
            case "405":
                imageId = R.mipmap.icon_405d;
                break;
            case "406":
                imageId = R.mipmap.icon_406d;
                break;
            case "407":
                imageId = R.mipmap.icon_407d;
                break;
            case "408":
                imageId = R.mipmap.icon_408d;
                break;
            case "409":
                imageId = R.mipmap.icon_409d;
                break;
            case "410":
                imageId = R.mipmap.icon_410d;
                break;
            case "499":
                imageId = R.mipmap.icon_499d;
                break;
            case "500":
                imageId = R.mipmap.icon_500d;
                break;
            case "501":
                imageId = R.mipmap.icon_501d;
                break;
            case "502":
                imageId = R.mipmap.icon_502d;
                break;
            case "503":
                imageId = R.mipmap.icon_503d;
                break;
            case "504":
                imageId = R.mipmap.icon_504d;
                break;
            case "507":
                imageId = R.mipmap.icon_507d;
                break;
            case "508":
                imageId = R.mipmap.icon_508d;
                break;
            case "509":
                imageId = R.mipmap.icon_509d;
                break;
            case "510":
                imageId = R.mipmap.icon_510d;
                break;
            case "511":
                imageId = R.mipmap.icon_511d;
                break;
            case "512":
                imageId = R.mipmap.icon_512d;
                break;
            case "513":
                imageId = R.mipmap.icon_513d;
                break;
            case "514":
                imageId = R.mipmap.icon_514d;
                break;
            case "515":
                imageId = R.mipmap.icon_515d;
                break;
            case "900":
                imageId = R.mipmap.icon_900d;
                break;
            case "901":
                imageId = R.mipmap.icon_901d;
                break;
            default:
                imageId = R.mipmap.icon_999d;
        }
        return imageId;
    }

    // 获取夜晚天气图标
    public static int getNightIcon(String iconNight) {
        int imageId;
        switch (iconNight) {
            case "100":
                imageId = R.mipmap.icon_100n;
                break;
            case "101":
                imageId = R.mipmap.icon_101n;
                break;
            case "102":
                imageId = R.mipmap.icon_102n;
                break;
            case "103":
                imageId = R.mipmap.icon_103n;
                break;
            case "104":
                imageId = R.mipmap.icon_104n;
                break;
            case "200":
                imageId = R.mipmap.icon_200n;
                break;
            case "201":
                imageId = R.mipmap.icon_210n;
                break;
            case "202":
                imageId = R.mipmap.icon_202n;
                break;
            case "203":
                imageId = R.mipmap.icon_203n;
                break;
            case "204":
                imageId = R.mipmap.icon_204n;
                break;
            case "205":
                imageId = R.mipmap.icon_205n;
                break;
            case "206":
                imageId = R.mipmap.icon_206n;
                break;
            case "207":
                imageId = R.mipmap.icon_207n;
                break;
            case "208":
                imageId = R.mipmap.icon_208n;
                break;
            case "209":
                imageId = R.mipmap.icon_209n;
                break;
            case "210":
                imageId = R.mipmap.icon_210n;
                break;
            case "211":
                imageId = R.mipmap.icon_211n;
                break;
            case "212":
                imageId = R.mipmap.icon_212n;
                break;
            case "213":
                imageId = R.mipmap.icon_213n;
                break;
            case "300":
                imageId = R.mipmap.icon_300n;
                break;
            case "301":
                imageId = R.mipmap.icon_301n;
                break;
            case "302":
                imageId = R.mipmap.icon_302n;
                break;
            case "303":
                imageId = R.mipmap.icon_303n;
                break;
            case "304":
                imageId = R.mipmap.icon_304n;
                break;
            case "305":
                imageId = R.mipmap.icon_305n;
                break;
            case "306":
                imageId = R.mipmap.icon_306n;
                break;
            case "307":
                imageId = R.mipmap.icon_307n;
                break;
            case "308":
                imageId = R.mipmap.icon_308n;
                break;
            case "309":
                imageId = R.mipmap.icon_309n;
                break;
            case "310":
                imageId = R.mipmap.icon_310n;
                break;
            case "311":
                imageId = R.mipmap.icon_311n;
                break;
            case "312":
                imageId = R.mipmap.icon_312n;
                break;
            case "313":
                imageId = R.mipmap.icon_313n;
                break;
            case "314":
                imageId = R.mipmap.icon_314n;
                break;
            case "315":
                imageId = R.mipmap.icon_315n;
                break;
            case "316":
                imageId = R.mipmap.icon_316n;
                break;
            case "317":
                imageId = R.mipmap.icon_317n;
                break;
            case "318":
                imageId = R.mipmap.icon_318n;
                break;
            case "399":
                imageId = R.mipmap.icon_399n;
                break;
            case "400":
                imageId = R.mipmap.icon_400n;
                break;
            case "401":
                imageId = R.mipmap.icon_401n;
                break;
            case "402":
                imageId = R.mipmap.icon_402n;
                break;
            case "403":
                imageId = R.mipmap.icon_403n;
                break;
            case "404":
                imageId = R.mipmap.icon_404n;
                break;
            case "405":
                imageId = R.mipmap.icon_405n;
                break;
            case "406":
                imageId = R.mipmap.icon_406n;
                break;
            case "407":
                imageId = R.mipmap.icon_407n;
                break;
            case "408":
                imageId = R.mipmap.icon_408n;
                break;
            case "409":
                imageId = R.mipmap.icon_409n;
                break;
            case "410":
                imageId = R.mipmap.icon_410n;
                break;
            case "499":
                imageId = R.mipmap.icon_499n;
                break;
            case "500":
                imageId = R.mipmap.icon_500n;
                break;
            case "501":
                imageId = R.mipmap.icon_501n;
                break;
            case "502":
                imageId = R.mipmap.icon_502n;
                break;
            case "503":
                imageId = R.mipmap.icon_503n;
                break;
            case "504":
                imageId = R.mipmap.icon_504n;
                break;
            case "507":
                imageId = R.mipmap.icon_507n;
                break;
            case "508":
                imageId = R.mipmap.icon_508n;
                break;
            case "509":
                imageId = R.mipmap.icon_509n;
                break;
            case "510":
                imageId = R.mipmap.icon_510n;
                break;
            case "511":
                imageId = R.mipmap.icon_511n;
                break;
            case "512":
                imageId = R.mipmap.icon_512n;
                break;
            case "513":
                imageId = R.mipmap.icon_513n;
                break;
            case "514":
                imageId = R.mipmap.icon_514n;
                break;
            case "515":
                imageId = R.mipmap.icon_515n;
                break;
            case "900":
                imageId = R.mipmap.icon_900n;
                break;
            case "901":
                imageId = R.mipmap.icon_901n;
                break;
            default:
                imageId = R.mipmap.icon_999n;
        }
        return imageId;
    }

    // 获取白天背景
    public static int getDayBack(String iconDayBack) {
        int imageId;
        switch (iconDayBack) {
            case "100":
                imageId = R.drawable.back_100d;
                break;
            case "101":
                imageId = R.drawable.back_101d;
                break;
            case "102":
                imageId = R.drawable.back_102d;
                break;
            case "103":
                imageId = R.drawable.back_103d;
                break;
            case "104":
                imageId = R.drawable.back_104d;
                break;
            case "300":
                imageId = R.drawable.back_300d;
                break;
            case "301":
                imageId = R.drawable.back_301d;
                break;
            case "302":
                imageId = R.drawable.back_302d;
                break;
            case "303":
                imageId = R.drawable.back_303d;
                break;
            case "304":
                imageId = R.drawable.back_304d;
                break;
            case "305":
                imageId = R.drawable.back_305d;
                break;
            case "306":
                imageId = R.drawable.back_306d;
                break;
            case "307":
                imageId = R.drawable.back_307d;
                break;
            case "308":
                imageId = R.drawable.back_308d;
                break;
            case "309":
                imageId = R.drawable.back_309d;
                break;
            case "310":
                imageId = R.drawable.back_310d;
                break;
            case "311":
                imageId = R.drawable.back_311d;
                break;
            case "312":
                imageId = R.drawable.back_312d;
                break;
            case "313":
                imageId = R.drawable.back_313d;
                break;
            case "314":
                imageId = R.drawable.back_314d;
                break;
            case "315":
                imageId = R.drawable.back_315d;
                break;
            case "316":
                imageId = R.drawable.back_316d;
                break;
            case "317":
                imageId = R.drawable.back_317d;
                break;
            case "318":
                imageId = R.drawable.back_318d;
                break;
            case "399":
                imageId = R.drawable.back_399d;
                break;
            case "400":
                imageId = R.drawable.back_400d;
                break;
            case "401":
                imageId = R.drawable.back_401d;
                break;
            case "402":
                imageId = R.drawable.back_402d;
                break;
            case "403":
                imageId = R.drawable.back_403d;
                break;
            case "404":
                imageId = R.drawable.back_404d;
                break;
            case "405":
                imageId = R.drawable.back_405d;
                break;
            case "406":
                imageId = R.drawable.back_406d;
                break;
            case "407":
                imageId = R.drawable.back_407d;
                break;
            case "408":
                imageId = R.drawable.back_408d;
                break;
            case "409":
                imageId = R.drawable.back_409d;
                break;
            case "410":
                imageId = R.drawable.back_410d;
                break;
            case "499":
                imageId = R.drawable.back_499d;
                break;
            case "500":
                imageId = R.drawable.back_500d;
                break;
            case "501":
                imageId = R.drawable.back_501d;
                break;
            case "502":
                imageId = R.drawable.back_502d;
                break;
            case "503":
                imageId = R.drawable.back_503d;
                break;
            case "504":
                imageId = R.drawable.back_504d;
                break;
            case "507":
                imageId = R.drawable.back_507d;
                break;
            case "508":
                imageId = R.drawable.back_508d;
                break;
            case "509":
                imageId = R.drawable.back_509d;
                break;
            case "510":
                imageId = R.drawable.back_510d;
                break;
            case "511":
                imageId = R.drawable.back_511d;
                break;
            case "512":
                imageId = R.drawable.back_512d;
                break;
            case "513":
                imageId = R.drawable.back_513d;
                break;
            case "514":
                imageId = R.drawable.back_514d;
                break;
            case "515":
                imageId = R.drawable.back_515d;
                break;
            case "900":
                imageId = R.drawable.back_900d;
                break;
            case "901":
                imageId = R.drawable.back_901d;
                break;
            default:
                imageId = R.drawable.back_999d;
        }
        return imageId;
    }

    // 获取夜晚背景
    public static int getNightBack(String iconNightBack) {
        int imageId;
        switch (iconNightBack) {
            case "104":
                imageId = R.drawable.back_104n;
                break;
            case "150":
                imageId = R.drawable.back_150n;
                break;
            case "151":
                imageId = R.drawable.back_151n;
                break;
            case "152":
                imageId = R.drawable.back_152n;
                break;
            case "153":
                imageId = R.drawable.back_153n;
                break;
            case "302":
                imageId = R.drawable.back_302n;
                break;
            case "303":
                imageId = R.drawable.back_303n;
                break;
            case "304":
                imageId = R.drawable.back_304n;
                break;
            case "305":
                imageId = R.drawable.back_305n;
                break;
            case "306":
                imageId = R.drawable.back_306n;
                break;
            case "307":
                imageId = R.drawable.back_307n;
                break;
            case "308":
                imageId = R.drawable.back_308n;
                break;
            case "309":
                imageId = R.drawable.back_309n;
                break;
            case "310":
                imageId = R.drawable.back_310n;
                break;
            case "311":
                imageId = R.drawable.back_311n;
                break;
            case "312":
                imageId = R.drawable.back_312n;
                break;
            case "313":
                imageId = R.drawable.back_313n;
                break;
            case "314":
                imageId = R.drawable.back_314n;
                break;
            case "315":
                imageId = R.drawable.back_315n;
                break;
            case "316":
                imageId = R.drawable.back_316n;
                break;
            case "317":
                imageId = R.drawable.back_317n;
                break;
            case "318":
                imageId = R.drawable.back_318n;
                break;
            case "350":
                imageId = R.drawable.back_350n;
                break;
            case "351":
                imageId = R.drawable.back_351n;
                break;
            case "399":
                imageId = R.drawable.back_399n;
                break;
            case "400":
                imageId = R.drawable.back_400n;
                break;
            case "401":
                imageId = R.drawable.back_401n;
                break;
            case "402":
                imageId = R.drawable.back_402n;
                break;
            case "403":
                imageId = R.drawable.back_403n;
                break;
            case "404":
                imageId = R.drawable.back_404n;
                break;
            case "405":
                imageId = R.drawable.back_405n;
                break;
            case "408":
                imageId = R.drawable.back_408n;
                break;
            case "409":
                imageId = R.drawable.back_409n;
                break;
            case "410":
                imageId = R.drawable.back_410n;
                break;
            case "456":
                imageId = R.drawable.back_456n;
                break;
            case "457":
                imageId = R.drawable.back_457n;
                break;
            case "499":
                imageId = R.drawable.back_499n;
                break;
            case "500":
                imageId = R.drawable.back_500n;
                break;
            case "501":
                imageId = R.drawable.back_501n;
                break;
            case "502":
                imageId = R.drawable.back_502n;
                break;
            case "503":
                imageId = R.drawable.back_503n;
                break;
            case "504":
                imageId = R.drawable.back_504n;
                break;
            case "507":
                imageId = R.drawable.back_507n;
                break;
            case "508":
                imageId = R.drawable.back_508n;
                break;
            case "509":
                imageId = R.drawable.back_509n;
                break;
            case "510":
                imageId = R.drawable.back_510n;
                break;
            case "511":
                imageId = R.drawable.back_511n;
                break;
            case "512":
                imageId = R.drawable.back_512n;
                break;
            case "513":
                imageId = R.drawable.back_513n;
                break;
            case "514":
                imageId = R.drawable.back_514n;
                break;
            case "515":
                imageId = R.drawable.back_515n;
                break;
            case "900":
                imageId = R.drawable.back_900n;
                break;
            case "901":
                imageId = R.drawable.back_901n;
                break;
            default:
                imageId = R.drawable.back_999n;
                break;
        }
        return imageId;
    }
}