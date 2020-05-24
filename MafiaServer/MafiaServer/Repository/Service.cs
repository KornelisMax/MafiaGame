using MafiaServer.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace MafiaServer.Repository
{
    public class Service
    {
        public void KillPlayer(MafiaContext _context)
        {
            var votedPlayerId = _context.Votes
                                        .GroupBy(x => x.VotedPlayerId)
                                        .OrderByDescending(z => z.Count())
                                        .Take(1)
                                        .Select(t => t.Key)
                                        .FirstOrDefault();
            _context.Players
                    .Where(x => x.PlayerId == votedPlayerId)
                    .FirstOrDefault()
                    .IsAlive = false;
            var votes = _context.Votes.ToList();
            _context.Votes.RemoveRange(votes);
            _context.SaveChanges();

        }

        public string WinningSide(MafiaContext _context, string votingPlayer)
        {

            var currentRoom = _context.Players
                                        .Where(x => x.Name == votingPlayer)
                                        .FirstOrDefault().RoomId;

            var playersInSameRoom = _context.Players
                                            .Where(x => x.RoomId == currentRoom)
                                            .ToList();

            var winningSide = playersInSameRoom
                                            .GroupBy(x => x.Role)
                                            .OrderByDescending(z => z.Count())
                                            .Take(playersInSameRoom.Count)
                                            .Select(t => t.Key)
                                            .FirstOrDefault();
            return winningSide;
        }

        public int WhichSideWon(MafiaContext _context, string votingPlayer)
        {
            bool isWinningCivil;
            var currentRoom = _context.Players
                                        .Where(x => x.Name == votingPlayer)
                                        .FirstOrDefault().RoomId;

            var playersInSameRoom = _context.Players
                                            .Where(x => x.RoomId == currentRoom)
                                            .ToList();

            if(isWinningCivil = _context.Players
                                            .Any(x => x.IsAlive == false && x.Role.Equals("Mafia")))
            {
                return 2;
            }

            if (isWinningCivil = _context.Players
                                            .Any(x => x.IsAlive == false && x.Role.Equals("Civil")))
            {
                return 1;
            }
            return 0;
        }


    }
}
