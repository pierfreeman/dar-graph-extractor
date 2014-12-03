dar-graph-extractor
===================

A graph creator from a JSON dataset of plenary sessions of Portuguese parliament of the 2011-2013 Legislature.

This dataset can be found here: https://github.com/transparenciahackday/dar-json. It contains all the speech and interventions of the parliament politics during each session.

The graph represents the relationships between politics whereas there is an interruption during a speech: if politic B interrupt politic A during a speech of this last one, the two politics (nodes) have a relation (edge) between them.

The textual graph will be then imported in a graph tool (http://gephi.github.io/) for performing analyses.

The criterions for the graph extraction will change in the future as analyses will carry out particular results. 
