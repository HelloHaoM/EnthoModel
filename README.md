# EnthoModel
A Simulation Model for Ethnocentrism.

The system will have four steps:
* Immigration. The system will generate agents up to IMMIGRANTS-PER-DAY, with randomly selected traits in vacant locations.

* Interaction. Each agent has an initial rate (Potential-To-Reproduce) to reproduce their offspring. Each pair of adjacent agents interacts with each other based on their traits to decide whether or not to help the other. Agents will gain/lose PTR if they decide to help others.

* Reproduction. Each agent has the probability (PTR) to reproduce its offspring in an adjacent vacant location. The offspring has the same traits as its parent while it has a MUTATION-RATE to mutate its traits.

* Death. Each agent has a DEATH-RATE of dying. If it dies, the location will become vacant
