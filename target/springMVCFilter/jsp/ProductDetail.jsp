<!-- <%@page isELIgnored="false"%>使用EL表达式时加入此句-->
<%@page isELIgnored="false"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Save Product</title>
        <style type="text/css">@import url(../css/main.css);</style>
    </head>
    <body>
        <div id="global">
            <h4>The product has been saved.</h4>
            <p>
                <h5>Detail: </h5>
                Product Name: ${product.name}<br/>
                Description: ${product.description}<br/>
                Price: $${product.price}
            </p>
        </div>
    </body>
</html>