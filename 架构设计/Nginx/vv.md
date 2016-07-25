
#user  nobody;
worker_processes  1;

#error_log  logs/error.log;
#error_log  logs/error.log  notice;
#error_log  logs/error.log  info;

#pid        logs/nginx.pid;


events {
    worker_connections  1024;
}


http {
    include       mime.types;
    default_type  application/octet-stream;
    proxy_temp_path /usr/local/nginx/proxy_temp;
    proxy_cache_path /usr/local/nginx/proxy_cache_path levels=1:2 keys_zone=cache_one:200m inactive=1d max_size=1g;
    log_format cache '***$time_local '  '***$upstream_cache_status '  '***Cache-Control: $upstream_http_cache_control ' '***Expires: $upstream_http_expires ' '***"$request" ($status) ' '***"$http_user_agent" ';  
    access_log  /usr/local/nginx/logs/cache.log cache;
    sendfile        on;
    client_max_body_size 100m;
    #tcp_nopush     on;

    #keepalive_timeout  0;
    keepalive_timeout  65;

    gzip on;     
    gzip_min_length  1k;     
    gzip_buffers 4 16k;     
    gzip_http_version 1.1;     
    gzip_comp_level 2;     
    gzip_types text/plain application/x-javascript text/css application/xml;     
    gzip_vary on;  
 
upstream  tcp.tempus.cn   {
              server   172.16.13.94:8080;  
              server   172.16.13.95:8080;
	      server   172.16.13.96:8080;
    }

    server {
        listen       80;
        server_name  tcp.tempus.cn;
proxy_connect_timeout 300;
proxy_read_timeout 300;
proxy_send_timeout 300;
proxy_buffer_size 64k;
proxy_buffers   4 32k;
proxy_busy_buffers_size 64k;
proxy_temp_file_write_size 64k; 

        #charset koi8-r;

        #access_log  logs/host.access.log  main;

location ~ /purge(/.*)
    {
       allow 127.0.0.1;
       allow 172.16.50.250;
       deny  all;
       proxy_cache_purge cache_one $host:$server_port$1$is_args$args;
    }
location / {
  proxy_set_header  Host $host;  
  proxy_set_header  X-Real-IP  $remote_addr;
  proxy_set_header  x-forwarded-for $remote_addr;
  proxy_pass http://tcp.tempus.cn;
 }
# location ~ .*\.(gif|jpg|jpeg|png|bmp|swf|js|css|html|ico)$  
location ~ /static(/.*) 
    {  
          proxy_cache cache_one;  
          proxy_cache_methods GET HEAD POST;  
          proxy_cache_min_uses 1;  
          proxy_cache_valid 200 302 10m;  
          proxy_cache_valid 404 1m;  
          proxy_cache_valid any 1m;  
          proxy_cache_key "$host:$server_port$uri$is_args$args";  
          proxy_redirect off;  
          proxy_set_header Host $host;  
          proxy_set_header X-Real-IP $remote_addr;  
          proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;  
          root /var/www/web/ROOT;
     }
        #error_page  404              /404.html;

        # redirect server error pages to the static page /50x.html
        #
        error_page   500 502 503 504  /50x.html;
        location = /50x.html {
            root   html;
        }

        # proxy the PHP scripts to Apache listening on 127.0.0.1:80
        #
        #location ~ \.php$ {
        #    proxy_pass   http://127.0.0.1;
        #}

        # pass the PHP scripts to FastCGI server listening on 127.0.0.1:9000
        #
        #location ~ \.php$ {
        #    root           html;
        #    fastcgi_pass   127.0.0.1:9000;
        #    fastcgi_index  index.php;
        #    fastcgi_param  SCRIPT_FILENAME  /scripts$fastcgi_script_name;
        #    include        fastcgi_params;
        #}

        # deny access to .htaccess files, if Apache's document root
        # concurs with nginx's one
        #
        #location ~ /\.ht {
        #    deny  all;
        #}
    }


    # another virtual host using mix of IP-, name-, and port-based configuration
    #
    #server {
    #    listen       8000;
    #    listen       somename:8080;
    #    server_name  somename  alias  another.alias;

    #    location / {
    #        root   html;
    #        index  index.html index.htm;
    #    }
    #}


    # HTTPS server
    #
    #server {
    #    listen       443 ssl;
    #    server_name  localhost;

    #    ssl_certificate      cert.pem;
    #    ssl_certificate_key  cert.key;

    #    ssl_session_cache    shared:SSL:1m;
    #    ssl_session_timeout  5m;

    #    ssl_ciphers  HIGH:!aNULL:!MD5;
    #    ssl_prefer_server_ciphers  on;

    #    location / {
    #        root   html;
    #        index  index.html index.htm;
    #    }
    #}

}
