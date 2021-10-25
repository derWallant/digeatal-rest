<html>
<body>
	<h2>OData Olingo V4 Demo Service</h2>
	<a href="DemoService.svc/">OData Olingo V4 Demo Service</a>
	<h3>Sample Links</h3>
	<ul>
		<li>
			<h4>Infos</h4>
			<ul>
				<li><a href="digeatalData/$metadata">Metdadaten - $metadata</a></li>
			</ul>
		</li>
		<li>
			<h4>Read Entity Sets</h4>
			<ul>
				<li><a href="digeatalData/Restaurants">Restaurant Entities
						- /Restaurants</a></li>
				<li><a href="digeatalData/Products">Product Entities -
						/Products</a></li>
			</ul>
		</li>
		<!--   <li>
        <h4>Navigation between Entity and Entity Sets</h4>
        <ul>
            <li>
                <a href="DemoService.svc/Products(1)/Category">Category of first Product - /Products(1)/Category</a>
            </li>
            <li>
                <a href="DemoService.svc/Categories(1)/Products">Products of first Category - /Categories(1)/Products</a>
            </li>
        </ul>
    </li>
    <li>
        <h4>Top, Count, Skip for Entity Sets</h4>
        <ul>
            <li>
                <a href="DemoService.svc/Products/?$top=3">Top three of Products - /Products/?$top=3</a>
            </li>
            <li>
                <a href="DemoService.svc/Products/?$count=true">Products with count - /Products/?$count=true</a>
            </li>
            <li>
                <a href="DemoService.svc/Products/?$skip=2">Skip two Products - /Products/?$skip=2</a>
            </li>
            <li>
                <a href="DemoService.svc/Products/?$skip=2&$top=2&$count=true">Skip two, get first two and count all
                    Products - /Products/?$skip=2&$top=2&$count=true</a>
            </li>
        </ul>
    </li>
    <li>
        <h4>Order by ... of Entity Sets</h4>
        <ul>
            <li>
                <a href="DemoService.svc/Products/?$orderby=Name">Products ordered by name -
                    /Products/?$orderby=Name</a>
            </li>
            <li>
                <a href="DemoService.svc/Products/?$orderby=Name&$select=Name">Products ordered by name and select
                    name only - /Products/?$orderby=Name&$select=Name</a>
            </li>
        </ul>
    </li>
    <li>
        <h4>Filtered Entity Sets</h4>
        <ul>
            <li>
                <a href="DemoService.svc/Products?$filter=contains(Name,%27Screen%27)">Products which name contains
                    screen - /Products?$filter=contains(Name,%27Screen%27)</a>
            </li>
            <li>
                <a href="DemoService.svc/Products/?$filter=ID%20gt%204">Products which id is greater then 4 -
                    /Products/?$filter=ID%20gt%204x</a>
            </li>
        </ul>
    </li>
    <li>
        <h4>Expand of Entities and Entity Sets</h4>
        <ul>
            <li>
                <a href="DemoService.svc/Products(1)/?$expand=Category">Expand - /Products(1)/?$expand=Category</a>
            </li>
            <li>
                <a href="DemoService.svc/Products/?$expand=Category">Expand - /Products/?$expand=Category</a>
            </li>
            <li>
                <a href="DemoService.svc/Products(1)/?$expand=*">Expand - /Products(1)/?$expand=*</a>
            </li>
            <li>
                <a href="DemoService.svc/Categories(1)/?$expand=Products">Expand - /Categories(1)/?$expand=Products</a>
            </li>
            <li>
                <a href="DemoService.svc/Categories/?$expand=Products">Expand - /Categories/?$expand=Products</a>
            </li>
            <li>
                <a href="DemoService.svc/Categories/?$expand=*">Expand - /Categories/?$expand=*</a>
            </li>
        </ul>
    </li> -->
	</ul>
</body>
</html>