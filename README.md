# graph-page-navigation

TODO: <br>
  Update it to be for mobile only. [PRIORITY 1] <br>
  Store the loading time of each edge in a json file and use it wen loading the entire graph. [PRIORITY 1] <br>
  Weight of the @Edge should point to the graph json file. [PRIORITY 2] <br>
  Graph should maintain state of current page. [PRIORITY 3] <br>

DONE: <br>
  Notate navigation methods, so then we can map edges via reflection. <br>
  Set the weight based on loading time. <br>


Graph json file generated example: <br>
```
{
  "Graph": [
    {
      "Vertex": "GooglePage",
      "Edges": [
        {
          "Weight": 1530,
          "Destination": {
            "Vertex": "GoogleResults"
          }
        }
      ]
    },
    {
      "Vertex": "GoogleImages",
      "Edges": [
        {
          "Weight": 1030,
          "Destination": {
            "Vertex": "GoogleResults"
          }
        }
      ]
    },
    {
      "Vertex": "GoogleResults",
      "Edges": [
        {
          "Weight": 1327,
          "Destination": {
            "Vertex": "GoogleImages"
          }
        }
      ]
    }
  ]
}
```
