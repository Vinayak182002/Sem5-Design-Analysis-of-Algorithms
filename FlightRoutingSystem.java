import java.util.*;

class Airport 
{
	private final String code;
	private final String name;

	public Airport(String code, String name) 
	{
  		this.code = code;
  		this.name = name;
 	}

 	public String getCode() 
	{
  		return code;
 	}

 	public String getName() 
	{
  		return name;
 	}
}

class FlightRoute 
{
	private final Airport source;
	private final Airport destination;
	private final int distance;

 	public FlightRoute(Airport source, Airport destination, int distance) 
	{
  		this.source = source;
  		this.destination = destination;
  		this.distance = distance;
 	}

	public Airport getSource() 
	{
		return source;
	}

	public Airport getDestination() 
	{
		return destination;
	}

	public int getDistance() 
	{
		return distance;
	}
}

public class FlightRoutingSystem 
{
	private final Map<String, Airport> airports;
	private final List<FlightRoute> routes;

	public FlightRoutingSystem() 
	{
  		airports = new HashMap<>();
  		routes = new ArrayList<>();
 	}

	public void addAirport(String code, String name) 
	{
		airports.put(code, new Airport(code, name));
	}

	public void addRoute(String sourceCode, String destinationCode, int distance) 
	{
  		Airport source = airports.get(sourceCode);
  		Airport destination = airports.get(destinationCode);
  		if (source != null && destination != null) 
		{
   			routes.add(new FlightRoute(source, destination, distance));
  	}
 }

public List<List<Airport>> findAllRoutes(String sourceCode, String destinationCode) 
{
	Airport source = airports.get(sourceCode);
  	Airport destination = airports.get(destinationCode);

  	if (source == null || destination == null) 
	{
   		return null; // Source or destination airport not found
  	}

  	List<List<Airport>> allRoutes = new ArrayList<>();
  	List<Airport> currentRoute = new ArrayList<>();
  	findRoutesDFS(source, destination, allRoutes, currentRoute);

  	return allRoutes;
}

private void findRoutesDFS(Airport currentAirport, Airport destination, List<List<Airport>> allRoutes,
   List<Airport> currentRoute) 
{
 	currentRoute.add(currentAirport);

  	if (currentAirport == destination) 
	{
   		allRoutes.add(new ArrayList<>(currentRoute));
  	} 
	else 
	{
   		for (FlightRoute route : routes) 
		{
    			if (route.getSource() == currentAirport && !currentRoute.contains(route.getDestination())) 
			{
     			findRoutesDFS(route.getDestination(), destination, allRoutes, currentRoute);
    			}
   		}
 	}
	currentRoute.remove(currentRoute.size() - 1);
}

public void displayAllRoutes(List<List<Airport>> allRoutes) 
{
	if (allRoutes != null && !allRoutes.isEmpty()) 
	{
		System.out.println("\n=================================================");
   		System.out.println("All Available Routes are:");

   		List<Airport> shortestRoute = null;
   		int shortestDistance = Integer.MAX_VALUE;

   		for (List<Airport> route : allRoutes) 
		{
    			int totalDistance = 0;
    			System.out.print(route.get(0).getCode());

    			for (int i = 1; i < route.size(); i++) 
			{
     				for (FlightRoute flightRoute : routes) 
				{
      					if (flightRoute.getSource() == route.get(i - 1) && flightRoute.getDestination() == 						route.get(i)) 
					{
      					 	totalDistance += flightRoute.getDistance();
       						System.out.print(" to " + route.get(i).getCode());
       						break;
      					}
     				}
    			}

    			System.out.println(" Distance: " + totalDistance);

    			if (totalDistance < shortestDistance) 
			{
     				shortestDistance = totalDistance;
     				shortestRoute = route;
   			}
		System.out.println("\n=================================================");
   	}

   	System.out.println("\nShortest Route:");
   	if (shortestRoute != null) 
	{
		System.out.println("\n=================================================");
    		for (Airport airport : shortestRoute) 
		{
     			System.out.println(airport.getCode() + " - " + airport.getName());
    		}
    		System.out.println("Distance: " + shortestDistance);
		System.out.println("\n=================================================");
   	} 
	else 
	{
    		System.out.println("No routes found.");
   	}
 	 } 
	else 
	{
   		System.out.println("No routes found.");
  	}
}

public void displayAirports() 
{
	System.out.println("\n=================================================");
  	System.out.println("Airports:");
  	for (Airport airport : airports.values()) 
	{
   		System.out.println(airport.getCode() + " - " + airport.getName());
  	}
	System.out.println("\n=================================================");
}

public void displayRoutes() 
{
	System.out.println("\n=================================================");
	System.out.println("Flight Routes:");
  	for (FlightRoute route : routes) 
	{
   		System.out.println(route.getSource().getCode() + " to " + route.getDestination().getCode() + " Distance: " + 			route.getDistance());
  	}
	System.out.println("\n=================================================");
}

public void displayShortestRoute(List<Airport> shortestRoute) 
{
	if (shortestRoute != null) 
	{
		System.out.println("\n=================================================");
   		System.out.println("Shortest Route:");
   		for (Airport airport : shortestRoute) 
		{
    			System.out.println(airport.getCode() + " - " + airport.getName());
   		}
		System.out.println("\n=================================================");
  	} 
	else 
	{
   		System.out.println("No route found.");
  	}
}

public static void main(String[] args) 
{
	FlightRoutingSystem routingSystem = new FlightRoutingSystem();
  	Scanner scanner = new Scanner(System.in);

  	while (true) 
	{
		System.out.println("\n=================================================");
   		System.out.println("\nFlight Routing System Menu:");
   		System.out.println("1. Add Airport");
   		System.out.println("2. Add Flight Route");
   		System.out.println("3. Find Shortest Route");
   		System.out.println("4. Display Airports");
   		System.out.println("5. Display Flight Routes");
   		System.out.println("6. Exit");
		System.out.println("\n=================================================");
   		System.out.print("Select an option: ");

   		int choice = scanner.nextInt();
   		scanner.nextLine(); // Consume newline

   		switch (choice) 
		{
   			case 1:
				System.out.println("\n=================================================");
				System.out.println("\n====================ADD AIRPORT==================");
				System.out.println("\n=================================================");
    				System.out.print("Enter Airport Code: ");
    				String airportCode = scanner.nextLine();
    				System.out.print("Enter Airport Name: ");
    				String airportName = scanner.nextLine();
    				routingSystem.addAirport(airportCode, airportName);
    				System.out.println("Airport added: " + airportCode);
    				break;

   			case 2:
				System.out.println("\n=================================================");
				System.out.println("\n=================ADD FLIGHT ROUTE================");
				System.out.println("\n=================================================");
    				System.out.print("Enter Source Airport Code: ");
    				String sourceCode = scanner.nextLine();
    				System.out.print("Enter Destination Airport Code: ");
    				String destinationCode = scanner.nextLine();
    				System.out.print("Enter Distance: ");
    				int distance = scanner.nextInt();
    				routingSystem.addRoute(sourceCode, destinationCode, distance);
    				System.out.println("Flight Route added: " + sourceCode + " to " + destinationCode);
    				break;

   			case 3:
				System.out.println("\n=================================================");
				System.out.println("\n=================FIND SHORTEST ROUTE=============");
				System.out.println("\n=================================================");
    				System.out.print("Enter Source Airport Code: ");
    				String sourceAirportCode = scanner.nextLine();
    				System.out.print("Enter Destination Airport Code: ");
    				String destinationAirportCode = scanner.nextLine();
    				List<List<Airport>> allRoutes = routingSystem.findAllRoutes(sourceAirportCode, destinationAirportCode);
    				routingSystem.displayAllRoutes(allRoutes);
    				break;

   			case 4:
				System.out.println("\n=================================================");
				System.out.println("\n=================DISPLAY AIRPORTS================");
				System.out.println("\n=================================================");
    				routingSystem.displayAirports();
    				break;

   			case 5:
				System.out.println("\n=================================================");
				System.out.println("\n=================DISPLAY ROUTES==================");
				System.out.println("\n=================================================");
    				routingSystem.displayRoutes();
    				break;

   			case 6:
    				System.out.println("Exiting Flight Routing System.");
    				System.exit(0);

   			default:
    				System.out.println("Invalid option. Please select a valid option.");
   		}
  	}
}
}


// Input==>
/*
 * Pune->Mumbai=120
 * Mumbai->Pune=140
 * Pune->Banglore=800
 * Pune->Chennai=500
 * Mumbai->chennai=700
 * chennai->banglore=200
 * mumbai->banglore=600
 * 
 *
 */