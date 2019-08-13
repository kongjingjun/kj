package springboot.hello.helloSpringboot.config.startConfig;

import org.yaml.snakeyaml.Yaml;
import springboot.hello.helloSpringboot.util.YmlMapUtil;

import java.io.InputStream;
import java.util.Map;

public class LoadYmlParam {


    /**
     * @return
     * @throws Exception
     */
    public static synchronized void getProperties() {
        if (YmlMapUtil.ymlMap == null || YmlMapUtil.ymlMap.size() == 0) {
//            try {
//                Yaml yaml = new Yaml();
//                URL url = Dictionary.class.getClassLoader().getResource("application.yml");
////                URL url = AppProperties.class.getClassLoader().getResource("application.yml");
//                if (url != null) {
//                    //获取test.yaml文件中的配置数据，然后转换为obj，
//                    Object obj =yaml.load(new FileInputStream(url.getFile()));
//                    System.out.println(obj);
//                    //也可以将值转换为Map
//                    map =(Map)yaml.load(new FileInputStream(url.getFile()));
//                    System.out.println(map);
//                    //通过map我们取值就可以了.
//                }
//            } catch (FileNotFoundException e) {
//                e.printStackTrace();
//            }

            Yaml yaml = new Yaml();
            InputStream is = LoadYmlParam.class.getClassLoader().getResourceAsStream("application-param.yml");
            YmlMapUtil.ymlMap = (Map) yaml.load(is);

        }
    }
}
