# BuildTickets 

BuildTickets is a Minecraft server plugin designed to help staff members manage builds by submitting and tracking build ideas, managing builder assignments, and monitoring progress efficiently.

## Plugin Features

- Allows your build team to organize itself with build tickets.
- Toggle Minecraft block physics using a command in-game.
- Toggle build permission using a command in-game.
- Custom menus for Builders with QoL changes.
- A buildchat, builderstatus... to make communicating easier!
- Works with MySQL and Redis
And a lot of other things that will help builders!

# Permissions
### Basic Permissions
| Name | Permission | Description                       |
| :--------  | :--------  | :-------------------------------- |
| Build Mode       | `build.mode`       | Gives permission for the Build Mode command. |
| Build Chat       | `buildchat.see`       | Gives permission to use, see... the Build chat. |
| Build Code       | `buildchat.toggle`       | Toggles the Build chat. |
| Build Physics       | `buildphysics.toggle`       | Toggles the Minecraft physics, to make building advanced things easier. |
| Help Message       | `buildtickets.general.help`       | Gives permission to use the Tickets command. |
| Open Ticket Gui       | `buildtickets.tickets.gui`       | Gives permission to accept, reject Tickets. |
| Create Ticket       | `buildtickets.tickets.create`       | Gives permission to accept, reject Tickets. |

### Ticket Permission (self)
| Name | Permission | Description                       |
| :--------  | :--------  | :-------------------------------- |
| Change Ticket Priority       | `buildtickets.tickets.change_priority`       | Gives permission to accept, reject Tickets. |
| Join Ticket       | `buildtickets.tickets.join`       | Gives permission to accept, reject Tickets. |
| Join Without Help       | `buildtickets.tickets.join_without_help`       | Gives permission to accept, reject Tickets. |
| Request Help       | `buildtickets.tickets.request_help`       | Gives permission to accept, reject Tickets. |
| Ticket Mark As Complete       | `buildtickets.tickets.complete.mark`       | Gives permission to accept, reject Tickets. |
| Ticket Confirm As Complete       | `buildtickets.tickets.complete.confirm`       | Gives permission to accept, reject Tickets. |
| Ticket Change Reason       | `buildtickets.tickets.change_reason`       | Gives permission to accept, reject Tickets. |
| Ticket Leave       | `buildtickets.tickets.leave`       | Gives permission to accept, reject Tickets. |
| Open Ticket Viewer       | `buildtickets.viewer.open`       | Gives permission to accept, reject Tickets. |

### Ticket Permission (other)
| Name | Permission | Description                       |
| :--------  | :--------  | :-------------------------------- |
| Change Ticket Priority Other       | `buildtickets.tickets.other.change_priority`       | Gives permission to accept, reject Tickets. |
| Join Ticket Other       | `buildtickets.tickets.other.join`       | Gives permission to accept, reject Tickets. |
| Join Other Without Help       | `buildtickets.tickets.other.join_without_help`       | Gives permission to accept, reject Tickets. |
| Request Help Other       | `buildtickets.tickets.other.request_help`       | Gives permission to accept, reject Tickets. |
| Ticket Mark As Complete Other       | `buildtickets.tickets.other.complete.confirm`       | Gives permission to accept, reject Tickets. |
| Ticket Confirm As Complete Other       | `buildtickets.tickets.other.change_reason`       | Gives permission to accept, reject Tickets. |
| Ticket Change Reason Other       | `buildtickets.tickets.other.complete.confirm`       | Gives permission to accept, reject Tickets. |
| ticket Leave Other       | `buildtickets.tickets.other.leave`       | Gives permission to accept, reject Tickets. |
| Open Ticket Viewer Other       | `buildtickets.viewer.other.open`       | Gives permission to accept, reject Tickets. |

### Ticket Permissions (panel)
| Name | Permission | Description                       |
| :--------  | :--------  | :-------------------------------- |
| Open Ticket Panel       | `buildtickets.panel.open`       | Gives permission to accept, reject Tickets. |
| Player Stats Panel       | `buildtickets.panel.player_stats`       | Gives permission to accept, reject Tickets. |
| Active Tickets Panel       | `buildtickets.panel.active_tickets`       | Gives permission to accept, reject Tickets. |

## FAQ

#### Does the plugin work on 1.8?

Currently, the plugin supports 1.13+ versions. We might add 1.8 support in the future.
