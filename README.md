# graph-po

TODO: <br>
  Remove BFS and use a library for the graph. [NOT REQUIRED RIGHT NOW]. <br>
  Store the loading time of each edge in a json file and use it wen loading the entire graph. [PRIORITY 2] <br>
  weight of the @Edge should point to the graph json file. [PRIORITY 3] <br>

DONE: <br>
  Notate navigation methods and use it to track, in some reflection way. <br>
    Set the weight based on loading time. [PRIORITY 1] <br>


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
