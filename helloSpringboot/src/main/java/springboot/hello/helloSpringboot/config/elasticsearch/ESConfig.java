package springboot.hello.helloSpringboot.config.elasticsearch;


import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.InetAddress;
import java.net.UnknownHostException;


//@Configuration
public class ESConfig {
   // @Bean
    public TransportClient client() throws UnknownHostException {

        //设置es连接地址 创建三个节点实例
        TransportAddress node1 = new TransportAddress(
                InetAddress.getByName("localhost"),
                9300
        );
        TransportAddress node2 = new TransportAddress(
                InetAddress.getByName("localhost"),
                9301
        );
        TransportAddress node3 = new TransportAddress(
                InetAddress.getByName("localhost"),
                9302
        );

        TransportClient client = null;
        try {
            //设置集群的参数配置(根据需要设置其他参数)
            Settings settings = Settings.builder()
                    .put("cluster.name", "myblog")
                    .build();
            //构造TransportClient实例
            client = new PreBuiltTransportClient(settings);
            client.addTransportAddress(node1);
            client.addTransportAddress(node2);
            client.addTransportAddress(node3);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return client;
    }
}
