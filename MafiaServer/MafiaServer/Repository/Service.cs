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
        }


    }
}
